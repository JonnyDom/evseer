package com.domingues4j.dto.housepower.battery;

import java.util.Arrays;
import java.util.function.Predicate;

public enum BatteryStatusDTO {
    CHARGING(a -> a > 0),
    DISCHARGING(a -> a < 0),
    NEUTRAL(a -> a == 0);

    private final Predicate<Double> batteryPowerPredicate;

    BatteryStatusDTO(Predicate<Double> batteryPowerPredicate) {
        this.batteryPowerPredicate = batteryPowerPredicate;
    }

    public static BatteryStatusDTO fromBatteryPower(double batteryPower) {
        return Arrays.stream(values())
                .filter(v -> v.batteryPowerPredicate.test(batteryPower))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
