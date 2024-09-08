package com.domingues4j.evseer.external.powerdevice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueTypeTest {

    private static final int MAX_NUM_OF_PANELS = 18;

    @Test
    void fromLabel() {
        assertEquals(ValueType.GRID_CONSUMPTION_POWER, ValueType.fromLabel("gridConsumptionPower"));
        assertEquals(ValueType.LOADS_POWER, ValueType.fromLabel("loadsPower"));
        assertEquals(ValueType.FEED_IN_POWER, ValueType.fromLabel("feedinPower"));
        assertEquals(ValueType.GENERATION_POWER, ValueType.fromLabel("generationPower"));
        assertEquals(ValueType.BATTERY_CHARGE_POWER, ValueType.fromLabel("batChargePower"));
        assertEquals(ValueType.BATTERY_DISCHARGE_POWER, ValueType.fromLabel("batDischargePower"));
        assertEquals(ValueType.STATE_OF_CHARGE, ValueType.fromLabel("SoC"));

        for (int i = 1; i <= MAX_NUM_OF_PANELS; i++) {
            String label = String.format("pv%dPower", i);
            String value = String.format("PANEL_%d_POWER", i);
            assertEquals(ValueType.valueOf(value), ValueType.fromLabel(label));
        }
    }
}