package com.aplose.aploseframework.service;

import com.aplose.aploseframework.model.Role;
import com.aplose.aploseframework.model.RoleEnum;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author oandrade
 */
@Service
public class UserAccountService implements UserDetailsService{


    @Value("${aplose.framework.superAdmin.defaultPassword}")
    private String superAdminDefaultPassword;
    
    @Autowired
    private UserAccountRepository _userAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService _roleService;

    
    @PostConstruct
    private void init(){
        //create first superadmin if needed
        UserAccount superAdmin = _userAccountRepository.findByUsername("SuperAdmin");
        if (superAdmin==null){
            superAdmin = new UserAccount();
            superAdmin.setCreationDate(LocalDate.now());
            superAdmin.setEnabled(Boolean.TRUE);
            superAdmin.setPassword(passwordEncoder.encode(superAdminDefaultPassword));
            superAdmin.setLocked(Boolean.FALSE);
            superAdmin.setUsername("SuperAdmin");
            Role superAdminRole = _roleService.getByAuthority(RoleEnum.ROLE_SUPER_ADMIN.toString());
            superAdmin.getRoles().add(superAdminRole);
            _userAccountRepository.save(superAdmin);
        }
        
    }


    @Override
    public UserAccount loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = _userAccountRepository.findByUsername(username);
        if(userAccount==null){
            throw new UsernameNotFoundException("User "+username+" not found.");
        }
        return userAccount;
    }


    public UserAccount getByActivationCode(String activationCode){
        UserAccount userAccount = _userAccountRepository.findByActivationCode(activationCode);
        return userAccount;
    }


    public UserAccount getById(Long userAccountId){
        return this._userAccountRepository.findById(userAccountId).orElseThrow(() -> new EntityNotFoundException("UserAccount not found in database"));
    }


    public UserAccount save(UserAccount userAccount){
        return this._userAccountRepository.save(userAccount);
    }


    public void delete(UserAccount userAccount){
        this._userAccountRepository.delete(userAccount);
    }


}
