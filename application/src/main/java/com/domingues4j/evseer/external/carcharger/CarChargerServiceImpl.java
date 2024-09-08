package com.domingues4j.evseer.external.carcharger;

import com.domingues4j.evseer.scheduler.SystemUpdateScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CarChargerServiceImpl implements CarChargerService {

    private static final Logger log = LoggerFactory.getLogger(SystemUpdateScheduler.class);

    public CarChargerServiceImpl() {
    }

    @Override
    public boolean requestChargingUpdate(boolean shouldCharge) {
        log.info("Car Charger has been requested to {}", shouldCharge ? "start" : "stop");
        return true;
    }

}
