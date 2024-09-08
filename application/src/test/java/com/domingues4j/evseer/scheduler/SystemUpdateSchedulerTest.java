package com.domingues4j.evseer.scheduler;

import com.domingues4j.evseer.business.PowerMonitoringService;
import com.domingues4j.evseer.business.PowerMonitoringServiceImpl;
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

class SystemUpdateSchedulerTest {

    private AutoCloseable mocks;

    @Mock
    private PowerMonitoringService powerMonitoringService;

    private SystemUpdateScheduler scheduler;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        scheduler = new SystemUpdateScheduler(powerMonitoringService);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testTriggerShouldCallMonitoringService() throws PowerDeviceServiceException {
        scheduler.triggerSystemUpdate();

        verify(powerMonitoringService).monitorHousePower();
    }
}