package com.domingues4j.evseer.business;

import com.domingues4j.dto.housepower.HousePowerDataDTO;
import com.domingues4j.dto.internal.SystemUpdateDTO;
import com.domingues4j.evseer.business.facade.PowerDataFacade;
import com.domingues4j.evseer.business.facade.PowerDeviceServiceException;
import com.domingues4j.evseer.external.carcharger.CarChargerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PowerMonitoringServiceImpl implements PowerMonitoringService {

    private static final Logger log = LoggerFactory.getLogger(PowerMonitoringServiceImpl.class);

    private final CarChargerService carChargerService;
    private final PowerDataFacade powerDataFacade;
    private final double carChargingPowerInWatts;

    @Autowired
    public PowerMonitoringServiceImpl(
            PowerDataFacade powerDataFacade,
            CarChargerService carChargerService,
            @Value("${electric.vehicle.charge.power.watts}") double carChargingPowerInWatts
    ) {
        this.carChargerService = carChargerService;
        this.powerDataFacade = powerDataFacade;
        this.carChargingPowerInWatts = carChargingPowerInWatts;
    }

    @Override
    public SystemUpdateDTO monitorHousePower() throws PowerDeviceServiceException {
        HousePowerDataDTO housePowerData = powerDataFacade.getSolarPowerData();

        log.info("Current house power status: {}", housePowerData);

        boolean shouldCarCharge = housePowerData.getSolarPanelProduction().watts() > carChargingPowerInWatts;
        carChargerService.requestChargingUpdate(shouldCarCharge);

        return new SystemUpdateDTO(shouldCarCharge);
    }
}
