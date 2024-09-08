package com.domingues4j.evseer.external.powerdevice;

import com.domingues4j.api.powerdevice.response.RealTimeInverterDataResponse;

public interface PowerDeviceService {

    RealTimeInverterDataResponse getDeviceRealTimeData();

}
