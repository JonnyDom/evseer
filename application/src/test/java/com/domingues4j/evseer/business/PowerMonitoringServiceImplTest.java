package com.domingues4j.evseer.business;

import com.domingues4j.dto.housepower.HousePowerDataDTO;
import com.domingues4j.dto.housepower.PowerDataUnitDTO;
import com.domingues4j.dto.internal.SystemUpdateDTO;
import com.domingues4j.evseer.business.facade.carcharger.CarChargerFacade;
import com.domingues4j.evseer.business.facade.powerdevice.PowerDataFacade;
import com.domingues4j.evseer.business.facade.powerdevice.PowerDeviceServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PowerMonitoringServiceImplTest {

    private static final int CHARGING_OUTLET_VOLTAGE = 220;
    private AutoCloseable mocks;

    @Mock
    private CarChargerFacade carChargerService;
    @Mock
    private PowerDataFacade powerDataFacade;

    private PowerMonitoringService service;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        service = new PowerMonitoringServiceImpl(powerDataFacade, carChargerService, CHARGING_OUTLET_VOLTAGE);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void monitorHousePowerShouldRequestChargingStart() throws PowerDeviceServiceException {
        when(powerDataFacade.getSolarPowerData()).thenReturn(
                HousePowerDataDTO.builder()
                        .solarPanelProduction(new PowerDataUnitDTO(2300))
                        .build());

        SystemUpdateDTO systemUpdateDTO = service.monitorHousePower();

        verify(carChargerService).requestChargingUpdate(true);
        assertTrue(systemUpdateDTO.carIsCharging());
    }

    @Test
    public void monitorHousePowerShouldRequestChargingStop() throws PowerDeviceServiceException {
        when(powerDataFacade.getSolarPowerData()).thenReturn(
                HousePowerDataDTO.builder()
                        .solarPanelProduction(new PowerDataUnitDTO(2000))
                        .build());

        SystemUpdateDTO systemUpdateDTO = service.monitorHousePower();

        verify(carChargerService).requestChargingUpdate(false);
        assertFalse(systemUpdateDTO.carIsCharging());
    }
}