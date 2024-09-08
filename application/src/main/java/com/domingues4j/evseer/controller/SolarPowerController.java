package com.domingues4j.evseer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolarPowerController {

    @GetMapping("/solar-power-status")
    public String getSolarPowerStatus() {
        return "NOT IMPLEMENTED YET - In the future this will return the current solar power status";
    }
}
