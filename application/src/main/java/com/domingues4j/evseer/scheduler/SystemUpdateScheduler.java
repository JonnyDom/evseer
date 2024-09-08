package com.domingues4j.evseer.scheduler;

import com.domingues4j.dto.internal.SystemUpdateDTO;
import com.domingues4j.evseer.business.PowerMonitoringService;
import com.domingues4j.evseer.business.facade.PowerDeviceServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemUpdateScheduler {
    private static final int TRIGGER_RATE_IN_MILLISECONDS = 60000; //Once per minute

    private static final Logger log = LoggerFactory.getLogger(SystemUpdateScheduler.class);

    private final PowerMonitoringService powerMonitoringService;

    @Autowired
    public SystemUpdateScheduler(PowerMonitoringService powerMonitoringService) {
        this.powerMonitoringService = powerMonitoringService;
    }

    @Scheduled(fixedRate = TRIGGER_RATE_IN_MILLISECONDS)
    public void triggerSystemUpdate() throws PowerDeviceServiceException {
        log.info("Triggering system update. . .");

        SystemUpdateDTO updateDTO = powerMonitoringService.monitorHousePower();

        log.info("System state: {}", updateDTO);
    }
}
