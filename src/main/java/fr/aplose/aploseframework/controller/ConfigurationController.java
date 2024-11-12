package fr.aplose.aploseframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.aplose.aploseframework.model.Config;
import fr.aplose.aploseframework.service.ConfigService;

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
        model.addAttribute("configs", configService.getAllConfigs());
//        model.addAttribute("configToUpdate", new Config());
        return "configuration";
    }
    @PostMapping
    public String updateConfigurationAdminScreen(@ModelAttribute Config config){
        configService.createOrUpdate(config);
        return "redirect:/admin/configuration";
    }
}
