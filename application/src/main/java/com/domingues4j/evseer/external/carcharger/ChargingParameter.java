package com.domingues4j.evseer.external.carcharger;

import java.util.Arrays;

public enum ChargingParameter {
    WORK_STATE("work_state"),
    CURRENT_AMPS_SET("charge_cur_set"),
    WORK_MODE("work_mode"),
    CHARGING_SWITCH("switch"),
    UNKNOWN("UNKNOWN");

    private final String code;

    ChargingParameter(String code) {
        this.code = code;
    }

    public static ChargingParameter fromCode(String code) {
        return Arrays.stream(values())
                .filter(parameter -> parameter.code.equals(code))
                .findFirst().orElse(UNKNOWN);
    }
}
