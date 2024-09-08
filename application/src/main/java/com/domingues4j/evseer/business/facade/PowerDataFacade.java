package com.domingues4j.evseer.business.facade;

import com.domingues4j.api.powerdevice.response.InverterDataUnit;
import com.domingues4j.api.powerdevice.response.RealTimeInverterDataResponse;
import com.domingues4j.api.powerdevice.response.RealTimeInverterResult;
import com.domingues4j.dto.housepower.HousePowerDataDTO;
import com.domingues4j.dto.housepower.PowerDataUnitDTO;
import com.domingues4j.dto.housepower.battery.BatteryDataDTO;
import com.domingues4j.evseer.external.powerdevice.PowerDeviceService;
import com.domingues4j.evseer.external.powerdevice.ValueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PowerDataFacade {

    private static final Function<InverterDataUnit, ValueType> VALUE_TYPE_FUNCTION = unit -> ValueType.fromLabel(unit.getVariable());

    private final PowerDeviceService powerDeviceService;

    @Autowired
    public PowerDataFacade(PowerDeviceService powerDeviceService) {
        this.powerDeviceService = powerDeviceService;
    }

    public HousePowerDataDTO getSolarPowerData() throws PowerDeviceServiceException {
        RealTimeInverterDataResponse response = powerDeviceService.getDeviceRealTimeData();

        if (!"success".equals(response.getMsg())) {
            throw new PowerDeviceServiceException(response.getErrno() + response.getMsg());
        }

        RealTimeInverterResult result = response.getResult().get(0);

        Map<ValueType, InverterDataUnit> dataUnitByType =
                result.getDatas().stream().collect(Collectors.toMap(VALUE_TYPE_FUNCTION, Function.identity()));

        double totalPanelProductionInWatts = computeTotalPanelProduction(dataUnitByType);

        HousePowerDataDTO.Builder builder = HousePowerDataDTO.builder()
                .solarPanelProduction(new PowerDataUnitDTO(totalPanelProductionInWatts))
                .houseLoad(new PowerDataUnitDTO(dataUnitByType.get(ValueType.LOADS_POWER).getValue() * 1000))
                .gridConsumption(new PowerDataUnitDTO(dataUnitByType.get(ValueType.GRID_CONSUMPTION_POWER).getValue() * 1000));

        // In case the system includes a battery
        if (dataUnitByType.containsKey(ValueType.STATE_OF_CHARGE)) {
            double batteryPowerStatus = computeBatteryPowerStatus(
                    dataUnitByType.get(ValueType.BATTERY_CHARGE_POWER).getValue() * 1000,
                    dataUnitByType.get(ValueType.BATTERY_DISCHARGE_POWER).getValue() * 1000);
            builder.batteryData(BatteryDataDTO.builder()
                    .batteryPowerStatus(new PowerDataUnitDTO(batteryPowerStatus))
                    .batteryStatusPercent(dataUnitByType.get(ValueType.STATE_OF_CHARGE).getValue())
                    .build());
        }

        return builder.build();
    }

    private double computeBatteryPowerStatus(Double chargePower, Double dischargePower) {
        return (chargePower > 0) ? chargePower : dischargePower * -1;
    }

    private static double computeTotalPanelProduction(Map<ValueType, InverterDataUnit> dataUnitByType) {
        return dataUnitByType.entrySet().stream()
                .filter(entry -> entry.getKey().isPanel())
                .map(Map.Entry::getValue)
                .map(InverterDataUnit::getValue)
                .mapToDouble(v -> v * 1000) // values from the api come in kW
                .reduce(Double::sum).orElseThrow();
    }
}
