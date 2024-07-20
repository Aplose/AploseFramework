/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplose.aploseframework.repository;

import com.aplose.aploseframework.model.Config;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author oandrade
 */
public interface ConfigRepository extends JpaRepository<Config,String> {
    @Query(value = "Select c.configKey From Config c")
    public List<String> getAllKeys();
}
