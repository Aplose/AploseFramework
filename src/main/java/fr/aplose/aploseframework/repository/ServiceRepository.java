package fr.aplose.aploseframework.repository;


import java.time.Duration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.aplose.aploseframework.model.Service;

/**
 *
 * @author oandrade
 */
public interface ServiceRepository extends JpaRepository<Service, Long>{

    @Query("SELECT s FROM Service s WHERE s.professional.address.country.code = :countryCode AND LOWER(s.name) LIKE LOWER(CONCAT('%',:name,'%')) AND s.duration BETWEEN :minDuration AND :maxDuration")
    public Page<Service> findByNameContainingIgnoreCase(
        @Param("name") String name, 
        @Param("countryCode") String countryCode,
        @Param("minDuration") Duration minDuration, 
        @Param("maxDuration") Duration maxDuration, 
        PageRequest pageRequest
    );

}
