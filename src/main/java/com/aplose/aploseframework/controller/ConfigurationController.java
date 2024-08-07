package com.aplose.aploseframework.controller;

import com.aplose.aploseframework.model.Config;
import com.aplose.aploseframework.service.ConfigService;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IHM 
 * @author oandrade
 */
@Controller
@RequestMapping("/admin/configuration")
public class ConfigurationController {
    @Autowired
    ConfigService configService;
    @GetMapping
    public String getConfigurationAdminScreen(Model model){
        model.addAttribute("configurations", configService.getAllConfigs());
        return "configuration";
    }
    @PostMapping
    public String updateConfigurationAdminScreen(@ModelAttribute Config config){
        configService.createOrUpdate(config);
        return "redirect:/admin/configuration";
    }
}
