package com.domingues4j.evseer.external.powerdevice;

import com.domingues4j.api.powerdevice.request.RealTimeInverterDataRequest;
import com.domingues4j.api.powerdevice.response.RealTimeInverterDataResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Component
public class PowerDeviceServiceImpl implements PowerDeviceService {

    private final String solarPowerProviderPath;
    private final String solarPowerProviderToken;
    private final String solarPowerProviderUrl;
    private final String deviceSerialNumber;

    private final VariableListFactory variableListFactory;
    private final PowerDeviceRequestTemplate requestTemplate;

    @Autowired
    public PowerDeviceServiceImpl(@Value("${solarpower.provider.host}") String solarPowerProviderHost,
                                  @Value("${solarpower.provider.path}") String solarPowerProviderPath,
                                  @Value("${solarpower.provider.token}") String solarPowerProviderToken,
                                  @Value("${solarpower.provider.sn}") String deviceSerialNumber,
                                  VariableListFactory variableListFactory,
                                  PowerDeviceRequestTemplate requestTemplate) {
        this.solarPowerProviderPath = solarPowerProviderPath;
        this.solarPowerProviderToken = solarPowerProviderToken;
        this.variableListFactory = variableListFactory;
        this.solarPowerProviderUrl = solarPowerProviderHost + solarPowerProviderPath;
        this.deviceSerialNumber = deviceSerialNumber;
        this.requestTemplate = requestTemplate;
    }

    public RealTimeInverterDataResponse getDeviceRealTimeData() {
        HttpHeaders headers = buildHttpHeaders();
        RealTimeInverterDataRequest requestPayload = buildRequestPayload();

        HttpEntity<RealTimeInverterDataRequest> request = new HttpEntity<>(requestPayload, headers);

        return requestTemplate.postForObject(solarPowerProviderUrl, request, RealTimeInverterDataResponse.class);
    }

    private RealTimeInverterDataRequest buildRequestPayload() {
        return new RealTimeInverterDataRequest(
                deviceSerialNumber,
                variableListFactory.getVariableList()
        );
    }

    private HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", solarPowerProviderToken);
        headers.set("lang", "en");
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        headers.set("timestamp", timestamp);

        String signature = solarPowerProviderPath + "\\r\\n" + solarPowerProviderToken + "\\r\\n" + timestamp;
        String md5Hex = DigestUtils.md5Hex(signature);
        headers.set("signature", md5Hex);
        return headers;
    }
}
