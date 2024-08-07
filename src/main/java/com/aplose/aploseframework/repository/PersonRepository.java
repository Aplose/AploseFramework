/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplose.aploseframework.repository;

import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.UserAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author oandrade
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    
    public Person findByLastName(String lastName);
    
    @Query("SELECT p.userAccount FROM Person p LEFT JOIN p.userAccount ua LEFT JOIN ua.roles r WHERE p.address.country.code=:countryCode AND r.authority='ROLE_PROFESSIONAL' AND LOWER(p.fullName) LIKE LOWER(CONCAT('%', :query, '%'))")
    public Page<UserAccount> findProfessionalsByFullNameContainingIgnoreCase(
        @Param("query") String query,
        @Param("countryCode") String countryCode,
        PageRequest pageRequest
    );
}
