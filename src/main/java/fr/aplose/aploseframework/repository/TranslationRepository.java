package fr.aplose.aploseframework.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.aplose.aploseframework.model.Translation;

/**
 *
 * @author oandrade
 */
public interface TranslationRepository extends JpaRepository<Translation, Long>{
    public Translation findByCodeAndLocale(String Code, String locale);
    public List<Translation> findByLocale(String locale);
}
