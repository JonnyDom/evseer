package com.domingues4j.evseer.business.facade.carcharger;

import com.domingues4j.api.carcharger.CarChargerStatus;
import com.domingues4j.api.carcharger.ChargingStatusParameter;
import com.domingues4j.dto.chargercontrol.ChargerState;
import com.domingues4j.evseer.external.carcharger.CarChargerService;
import com.domingues4j.evseer.external.carcharger.ChargingParameter;
import com.domingues4j.evseer.external.carcharger.WorkState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class CarChargerFacade  {

    private static final Logger log = LoggerFactory.getLogger(CarChargerFacade.class);

    private final CarChargerService carChargerService;

    @Autowired
    public CarChargerFacade(CarChargerService carChargerService) {
        this.carChargerService = carChargerService;
    }

    public boolean requestChargingUpdate(boolean shouldCharge) {
        log.info("Car Charger has been requested to {}", shouldCharge ? "start" : "stop");
        return shouldCharge;
    }

    public ChargerState getChargerStatus() {
        List<ChargingStatusParameter> parameters = carChargerService.getCarChargerCurrentStatus();

        Map<ChargingParameter, Object> paramterMap = parameters.stream().collect(
                toMap(param -> ChargingParameter.fromCode(param.getCode()), ChargingStatusParameter::getValue));

        return switch (WorkState.fromValue((String) paramterMap.get(ChargingParameter.WORK_STATE))) {
            case UNPLUGGED -> ChargerState.DISCONNECTED;
            case PLUGGED -> {
                if ((boolean) paramterMap.get(ChargingParameter.CHARGING_SWITCH)) {
                    yield ChargerState.CHARGING;
                } else {
                    yield ChargerState.NOT_CHARGING;
                }
            }
            case UNKNOWN -> ChargerState.DISCONNECTED;
        };
    }

}
