package com.domingues4j.evseer.scheduler;

import com.domingues4j.evseer.business.PowerMonitoringService;
import com.domingues4j.evseer.business.facade.powerdevice.PowerDeviceServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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