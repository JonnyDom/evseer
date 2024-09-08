package com.domingues4j.dto.housepower.battery;

import com.domingues4j.dto.housepower.PowerDataUnitDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BatteryDataDTO {

    private PowerDataUnitDTO batteryPowerStatus;
    private double batteryStatusPercent;

}
