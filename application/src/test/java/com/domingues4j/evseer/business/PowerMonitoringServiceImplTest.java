package com.domingues4j.evseer.business;

import com.domingues4j.dto.housepower.HousePowerDataDTO;
import com.domingues4j.dto.housepower.PowerDataUnitDTO;
import com.domingues4j.dto.internal.SystemUpdateDTO;
import com.domingues4j.evseer.business.facade.PowerDataFacade;
import com.domingues4j.evseer.business.facade.PowerDeviceServiceException;
import com.domingues4j.evseer.external.carcharger.CarChargerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PowerMonitoringServiceImplTest {

    private static final int CAR_CHARGING_POWER_IN_WATTS = 1000;
    private AutoCloseable mocks;

    @Mock
    private CarChargerService carChargerService;
    @Mock
    private PowerDataFacade powerDataFacade;

    private PowerMonitoringService service;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        service = new PowerMonitoringServiceImpl(powerDataFacade, carChargerService, CAR_CHARGING_POWER_IN_WATTS);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void monitorHousePowerShouldRequestChargingStart() throws PowerDeviceServiceException {
        when(powerDataFacade.getSolarPowerData()).thenReturn(
                HousePowerDataDTO.builder()
                        .solarPanelProduction(new PowerDataUnitDTO(1010))
                        .build());

        SystemUpdateDTO systemUpdateDTO = service.monitorHousePower();

        verify(carChargerService).requestChargingUpdate(true);
        assertTrue(systemUpdateDTO.carIsCharging());
    }

    @Test
    public void monitorHousePowerShouldRequestChargingStop() throws PowerDeviceServiceException {
        when(powerDataFacade.getSolarPowerData()).thenReturn(
                HousePowerDataDTO.builder()
                        .solarPanelProduction(new PowerDataUnitDTO(990))
                        .build());

        SystemUpdateDTO systemUpdateDTO = service.monitorHousePower();

        verify(carChargerService).requestChargingUpdate(false);
        assertFalse(systemUpdateDTO.carIsCharging());
    }
}