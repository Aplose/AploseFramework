package com.aplose.aploseframework.service;

import com.aplose.aploseframework.dto.RegisterDto;
import com.aplose.aploseframework.dto.UserAccountDto;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.RoleEnum;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.repository.UserAccountRepository;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Value("${spring.user-account.second-to-activate-account}")
    private Long secondToActivateAccount;
    
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserAccountRepository _userAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService _roleService;
    @Autowired
    private ModelMapper _modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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



    public UserAccountDto registerUserAccount(RegisterDto userAccountDto) {
        if( ! userAccountDto.getPassword().equals(userAccountDto.getPasswordRepeat())){
            throw new RegistrationException("Password and password repeat do not match.");
        }
        UserAccount userAccount = this._modelMapper.map(userAccountDto, UserAccount.class);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        if(userAccountDto.getIsProfessional()){
            userAccount.setRoles(List.of(this._roleService.getByAuthority(RoleEnum.ROLE_PROFESSIONAL.toString())));
        }
        this.setAndSendActivationCode(userAccount);
        return this._modelMapper.map(_userAccountRepository.save(userAccount), UserAccountDto.class);
    }
    

    
    public void reSendActivationCode(UserAccount userAccount){
        this.setAndSendActivationCode(userAccount);
        this._userAccountRepository.save(userAccount);
    }
    


    public void setAndSendActivationCode(UserAccount userAccount){
        DecimalFormat format = new DecimalFormat("0000");
        userAccount.setActivationCode(format.format(new SecureRandom().nextInt(9999)));
        userAccount.setActivationCodeInstant(Instant.now());
        emailService.sendRegistrationSuccessfullMessage(userAccount.getLocale(),
                userAccount.getActivationCode(),
                userAccount.getUsername());
    }



    public Boolean activationCodeIsExpired(UserAccount userAccount){
        return userAccount.getActivationCodeInstant().plusSeconds(secondToActivateAccount).isBefore(Instant.now()) ?
            true 
            : 
            false;
    }



    public void activateAccount(UserAccount userAccount) {
        userAccount.setEnabled(Boolean.TRUE);
        userAccount.setActivationCode(null);
        userAccount.setActivationCodeInstant(null);
        _userAccountRepository.save(userAccount);
    }

}
