package com.domingues4j.dto.housepower;

import com.domingues4j.dto.housepower.battery.BatteryDataDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(builderClassName = "Builder")
@ToString
public class HousePowerDataDTO {

    private PowerDataUnitDTO solarPanelProduction;
    private PowerDataUnitDTO gridConsumption;
    private PowerDataUnitDTO houseLoad;

    private BatteryDataDTO batteryData;
}
