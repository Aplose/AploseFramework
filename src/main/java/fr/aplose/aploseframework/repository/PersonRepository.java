package fr.aplose.aploseframework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.aplose.aploseframework.model.Person;
import fr.aplose.aploseframework.model.UserAccount;

/**
 *
 * @author oandrade
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    
    public Person findByLastName(String lastName);
    
    @Query("SELECT p FROM Person p LEFT JOIN p.userAccount ua LEFT JOIN ua.roles r WHERE p.address.country.code=:countryCode AND r.authority='ROLE_PROFESSIONAL' AND LOWER(p.fullName) LIKE LOWER(CONCAT('%', :query, '%'))")
    public Page<Person> findProfessionalsByFullNameContainingIgnoreCase(
        @Param("query") String query,
        @Param("countryCode") String countryCode,
        PageRequest pageRequest
    );


    public Person findByUserAccount(UserAccount userAccount);
}
