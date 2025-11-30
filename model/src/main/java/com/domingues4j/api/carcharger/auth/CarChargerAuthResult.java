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
public class CarChargerAuthResult implements Serializable {
    private String access_token;
    private long expire_time;
    private String refresh_token;
    private String uid;
}
