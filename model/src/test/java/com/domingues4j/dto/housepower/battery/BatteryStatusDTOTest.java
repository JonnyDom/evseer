package com.domingues4j.dto.housepower.battery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatteryStatusDTOTest {

    @Test
    public void testBatteryStatusFromBatteryPower() {
        assertEquals(BatteryStatusDTO.CHARGING, BatteryStatusDTO.fromBatteryPower(2.2));
        assertEquals(BatteryStatusDTO.DISCHARGING, BatteryStatusDTO.fromBatteryPower(-1.4));
        assertEquals(BatteryStatusDTO.NEUTRAL, BatteryStatusDTO.fromBatteryPower(0));
    }

}