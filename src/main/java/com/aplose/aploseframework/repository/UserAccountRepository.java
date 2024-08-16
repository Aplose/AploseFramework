/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplose.aploseframework.repository;

import com.aplose.aploseframework.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oandrade
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    public UserAccount findByUsername(String username);


    public UserAccount findByActivationCode(String activationCode);


    public UserAccount findByStripeLinkedAccountId(String stripeLinkedAccountId);
}
