package com.domingues4j.api.powerdevice.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class RealTimeInverterDataResponse implements Serializable {

    private String errno;
    private String msg;
    private List<RealTimeInverterResult> result;
}
