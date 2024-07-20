/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplose.aploseframework.repository;

import com.aplose.aploseframework.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oandrade
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role findByAuthority(String authority);
}
