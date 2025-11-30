package com.domingues4j.api.carcharger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargerStatusResult implements Serializable {

    Map<String, List<ChargingStatusParameter>> deviceStatusListByDeviceId;

}
