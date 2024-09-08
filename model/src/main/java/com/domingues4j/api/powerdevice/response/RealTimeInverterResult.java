package com.domingues4j.api.powerdevice.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class RealTimeInverterResult implements Serializable {

    //Serial Number of Inverter
    private String deviceSN;

    //Time of Data Update, utc time
    private String time;

    private List<InverterDataUnit> datas;
}
