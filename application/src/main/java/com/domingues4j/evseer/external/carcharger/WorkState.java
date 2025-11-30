package com.domingues4j.evseer.external.carcharger;

import java.util.Arrays;

public enum WorkState {

    UNPLUGGED("charger_free"),
    PLUGGED("charger_end"),
    UNKNOWN("UNKNOWN");

    private String value;

    WorkState(String value) {
        this.value = value;
    }

    public static WorkState fromValue(String value) {
        return Arrays.stream(values())
                .filter(state -> state.value.equals(value))
                .findFirst().orElse(UNKNOWN);
    }
}
