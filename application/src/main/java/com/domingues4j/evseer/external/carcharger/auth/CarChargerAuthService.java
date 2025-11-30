package com.domingues4j.evseer.external.carcharger.auth;

import com.domingues4j.api.carcharger.auth.CarChargerAuthResult;

public interface CarChargerAuthService {

    CarChargerAuthResult getAccessTokenWithSimpleMode();
}
