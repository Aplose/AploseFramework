package com.aplose.aploseframework.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@RequestMapping(path = "/api")
public class PingController {
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
