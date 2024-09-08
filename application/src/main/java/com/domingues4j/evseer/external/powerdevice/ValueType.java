package com.domingues4j.evseer.external.powerdevice;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ValueType {

    GRID_CONSUMPTION_POWER("gridConsumptionPower"),
    LOADS_POWER("loadsPower"),
    FEED_IN_POWER("feedinPower"),
    GENERATION_POWER("generationPower"),
    BATTERY_CHARGE_POWER("batChargePower"),
    BATTERY_DISCHARGE_POWER("batDischargePower"),
    STATE_OF_CHARGE("SoC"),

    /*********** Panels ************/
    PANEL_1_POWER("pv1Power"),
    PANEL_2_POWER("pv2Power"),
    PANEL_3_POWER("pv3Power"),
    PANEL_4_POWER("pv4Power"),
    PANEL_5_POWER("pv5Power"),
    PANEL_6_POWER("pv6Power"),
    PANEL_7_POWER("pv7Power"),
    PANEL_8_POWER("pv8Power"),
    PANEL_9_POWER("pv9Power"),
    PANEL_10_POWER("pv10Power"),
    PANEL_11_POWER("pv11Power"),
    PANEL_12_POWER("pv12Power"),
    PANEL_13_POWER("pv13Power"),
    PANEL_14_POWER("pv14Power"),
    PANEL_15_POWER("pv15Power"),
    PANEL_16_POWER("pv16Power"),
    PANEL_17_POWER("pv17Power"),
    PANEL_18_POWER("pv18Power");

    private final String label;
    private final static String panelRegex = "pv\\d+Power";

    ValueType(String label) {
        this.label = label;
    }

    public static ValueType fromLabel(String label) {
        return Arrays.stream(values())
                .filter(valueType -> valueType.label.equals(label))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public boolean isPanel() {
        return label.matches(panelRegex);
    }
}
