package com.domingues4j.evseer.external.carcharger.auth;

import com.domingues4j.api.carcharger.auth.CarChargerAuthResult;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public class CarChargerAuthServiceImpl implements CarChargerAuthService {

    private String clientId;
    private static String HTTP_METHOD = "GET";

    public CarChargerAuthServiceImpl(@Value("${car.charger.app.url}") String url,
                                     @Value("${car.charger.app.auth.path}") String authPath,
                                     @Value("${car.charger.app.client.id}") String clientId,
                                     @Value("${car.charger.app.secret}") String secret) {
        this.clientId = clientId;
    }

    @Override
    public CarChargerAuthResult getAccessTokenWithSimpleMode() {
        long timestamp = Instant.now().toEpochMilli();
        //String sign = SignCalculator.calcSign(clientId, timestamp, "",  )
        return null;
    }
}
