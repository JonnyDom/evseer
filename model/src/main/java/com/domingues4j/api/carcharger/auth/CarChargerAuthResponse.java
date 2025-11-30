package com.domingues4j.api.carcharger.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarChargerAuthResponse implements Serializable {
    private CarChargerAuthResult result;
    private boolean success;
    private long t;
    private String tid;
}
