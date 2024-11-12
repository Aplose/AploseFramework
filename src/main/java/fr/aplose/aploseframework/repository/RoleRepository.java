package fr.aplose.aploseframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.aplose.aploseframework.model.Role;

/**
 *
 * @author oandrade
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role findByAuthority(String authority);
}
