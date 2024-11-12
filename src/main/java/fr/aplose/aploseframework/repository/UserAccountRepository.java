package fr.aplose.aploseframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.aplose.aploseframework.model.UserAccount;

/**
 *
 * @author oandrade
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    public UserAccount findByUsername(String username);


    public UserAccount findByActivationCode(String activationCode);


    public UserAccount findByDolibarrThirdPartyId(Long dolibarrThirdPartyId);
}
