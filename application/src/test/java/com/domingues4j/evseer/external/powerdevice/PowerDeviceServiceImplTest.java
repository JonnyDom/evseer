package com.domingues4j.evseer.external.powerdevice;

import com.domingues4j.api.powerdevice.response.RealTimeInverterDataResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

class PowerDeviceServiceImplTest {

    private static final String HOST = "host";
    private static final String PATH = "path";
    private static final String TOKEN = "token";
    private static final String SERIAL_NUMBER = "serialNumber";
    AutoCloseable mocks;

    @Mock
    PowerDeviceRequestTemplate template;

    @Mock
    VariableListFactory variableListFactory;

    private PowerDeviceService service;

    @BeforeEach
    public void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        service = new PowerDeviceServiceImpl(
                HOST,
                PATH,
                TOKEN,
                SERIAL_NUMBER,
                variableListFactory,
                template);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void getDeviceRealTimeData() {
        service.getDeviceRealTimeData();

        verify(template).postForObject(eq(HOST + PATH), any(HttpEntity.class), eq(RealTimeInverterDataResponse.class));
    }
}