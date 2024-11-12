package fr.aplose.aploseframework.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.aplose.aploseframework.model.Config;

/**
 *
 * @author oandrade
 */
public interface ConfigRepository extends JpaRepository<Config,String> {
    @Query(value = "Select c.configKey From Config c")
    public List<String> getAllKeys();
}
