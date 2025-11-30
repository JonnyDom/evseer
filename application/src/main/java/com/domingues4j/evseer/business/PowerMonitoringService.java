package com.domingues4j.evseer.business;

import com.domingues4j.dto.internal.SystemUpdateDTO;
import com.domingues4j.evseer.business.facade.powerdevice.PowerDeviceServiceException;

public interface PowerMonitoringService {

    SystemUpdateDTO monitorHousePower() throws PowerDeviceServiceException;
}
