package com.domingues4j.api.powerdevice.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class InverterDataUnit implements Serializable {

    private String unit; //e.g. kw
    private String variable;
    private String name;
    private Double value;
}
