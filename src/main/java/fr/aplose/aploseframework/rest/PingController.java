package fr.aplose.aploseframework.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class PingController {
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
