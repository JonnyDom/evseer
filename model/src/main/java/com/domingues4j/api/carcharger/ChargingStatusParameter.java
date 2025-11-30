package com.domingues4j.api.carcharger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStatusParameter implements Serializable {

    private String code;
    private Object value;

}
