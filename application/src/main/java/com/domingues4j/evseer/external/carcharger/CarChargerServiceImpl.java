package com.domingues4j.evseer.external.carcharger;

import com.domingues4j.api.carcharger.ChargingStatusParameter;
import com.domingues4j.evseer.external.powerdevice.PowerDeviceRequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarChargerServiceImpl implements CarChargerService {

    private static final Logger log = LoggerFactory.getLogger(CarChargerServiceImpl.class);

    public CarChargerServiceImpl(@Value("${car.charger.device.id}") String deviceId,
                                 PowerDeviceRequestTemplate requestTemplate) {
    }

    @Override
    public List<ChargingStatusParameter> getCarChargerCurrentStatus() {

        return null;
    }

    @Override
    public boolean issueChargingCommand(boolean shouldCharge) {
        return false;
    }
}
