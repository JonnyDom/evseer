package com.domingues4j.evseer.external.carcharger;

import com.domingues4j.api.carcharger.ChargingStatusParameter;

import java.util.List;

public interface CarChargerService {

    List<ChargingStatusParameter> getCarChargerCurrentStatus();

    boolean issueChargingCommand(boolean shouldCharge);

}
