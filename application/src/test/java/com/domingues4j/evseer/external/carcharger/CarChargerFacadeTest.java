package com.domingues4j.evseer.external.carcharger;

import com.domingues4j.evseer.business.facade.carcharger.CarChargerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CarChargerFacadeTest {

    AutoCloseable mocks;

    private CarChargerFacade carChargerFacade;

    @Mock
    private CarChargerService carChargerService;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        carChargerFacade = new CarChargerFacade(carChargerService);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void requestChargingUpdateShouldReturnTrueForNow() {

    }
}