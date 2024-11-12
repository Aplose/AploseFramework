package fr.aplose.aploseframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.aplose.aploseframework.model.Permission;

/**
 *
 * @author oandrade
 */
public interface PermissionRepository extends JpaRepository<Permission, Object> {
    
}
