package com.domingues4j.evseer.business;

import com.domingues4j.dto.housepower.HousePowerDataDTO;
import com.domingues4j.dto.internal.SystemUpdateDTO;
import com.domingues4j.evseer.business.facade.carcharger.CarChargerFacade;
import com.domingues4j.evseer.business.facade.powerdevice.PowerDataFacade;
import com.domingues4j.evseer.business.facade.powerdevice.PowerDeviceServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PowerMonitoringServiceImpl implements PowerMonitoringService {

    private static final Logger log = LoggerFactory.getLogger(PowerMonitoringServiceImpl.class);

    private final CarChargerFacade carChargerService;
    private final PowerDataFacade powerDataFacade;
    private final double chargingOutletVoltage;

    @Autowired
    public PowerMonitoringServiceImpl(
            PowerDataFacade powerDataFacade,
            CarChargerFacade carChargerService,
            @Value("${charging.electrical.outlet.voltage}") double chargingOutletVoltage) {
        this.carChargerService = carChargerService;
        this.powerDataFacade = powerDataFacade;
        this.chargingOutletVoltage = chargingOutletVoltage;
    }

    @Override
    public SystemUpdateDTO monitorHousePower() throws PowerDeviceServiceException {
        HousePowerDataDTO housePowerData = powerDataFacade.getSolarPowerData();

        log.info("Current house power status: {}", housePowerData);

        // TODO replace 10 with the current configured Amps
        boolean shouldCarCharge = housePowerData.getSolarPanelProduction().watts() > chargingOutletVoltage * 10;
        System.out.println("Should car charge:" + shouldCarCharge);
        //carChargerService.requestChargingUpdate(shouldCarCharge);

        return new SystemUpdateDTO(shouldCarCharge);
    }
}
