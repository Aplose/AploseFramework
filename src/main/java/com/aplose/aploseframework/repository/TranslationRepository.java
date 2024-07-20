/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplose.aploseframework.repository;

import com.aplose.aploseframework.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oandrade
 */
public interface TranslationRepository extends JpaRepository<Translation, Long>{
    public Translation findByCodeAndLocale(String Code, String locale);
}
