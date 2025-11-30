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
public class CarChargerStatus implements Serializable {

    private ChargerStatusResult result;
    private boolean success;
    private long t;
    private String tid;

}
