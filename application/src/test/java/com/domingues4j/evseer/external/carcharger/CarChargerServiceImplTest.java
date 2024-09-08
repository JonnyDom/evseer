package com.domingues4j.evseer.external.carcharger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarChargerServiceImplTest {

    @Test
    public void requestChargingUpdateShouldReturnTrueForNow() {
        assertTrue(new CarChargerServiceImpl().requestChargingUpdate(true));
        assertTrue(new CarChargerServiceImpl().requestChargingUpdate(false));
    }

}