package com.domingues4j.api.powerdevice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealTimeInverterDataRequest implements Serializable {

    private String sn;
    private List<String> variables;

}
