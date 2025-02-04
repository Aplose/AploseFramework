package fr.aplose.aploseframework.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 *
 * @author oandrade
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public RestClient restClient(){
        return RestClient.builder().messageConverters(configurer ->{
            ObjectMapper om = objectMapper();
            configurer.add(0, new MappingJackson2HttpMessageConverter(om));
        }).build();
    }
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        return objectMapperBuilder().build();
    }
    @Bean
    @Primary
    public JsonFactory jsonFactory(){
        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxNestingDepth(Integer.MAX_VALUE)
                .maxNumberLength(Integer.MAX_VALUE)
            .maxStringLength(Integer.MAX_VALUE)  // Définir une longueur maximale très élevée
            .build();
        StreamReadConstraints.overrideDefaultStreamReadConstraints(constraints);
        JsonFactoryBuilder jfb = new JsonFactoryBuilder();
        jfb.streamReadConstraints(constraints);
        JsonFactory jf = jfb.build();
        return jf;
    }
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){        
        Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
        jackson2ObjectMapperBuilder
                .factory(jsonFactory());
        return jackson2ObjectMapperBuilder;
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.FRANCE);
        return slr;
    }    
    @Bean
    LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }    
    @Bean
    public TemplateEngine emailTemplateEngine(SpringTemplateEngine templateEngine, MessageSource messageSource) {
        // Resolver for HTML emails (except the editable one)
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(messageSource);
        return templateEngine;
    }
    private ITemplateResolver htmlTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/mails/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }            
}
