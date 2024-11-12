package fr.aplose.aploseframework.service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
        @Autowired
        ConfigService configService;
        @Autowired
	JavaMailSender emailSender;
        @Autowired
        private MessageSource messageSource;
        @Autowired
        private TemplateEngine templateEngine;
        private String fromEmail;
        private String rootUrl;
        
        @PostConstruct
        private void init(){
            fromEmail=configService.getStringConfig("aploseframework.mail.fromEmail");
            rootUrl=configService.getStringConfig("aploseframework.backend.root.url");
        }
        

        @Async
	public void sendRegistrationSuccessfullMessage(Locale locale, String activationCode, String toEmail) {
            Context ctx = new Context(locale);
            ctx.setVariable("activationCode", activationCode);   
            ctx.setVariable("logoLink", rootUrl+"/assets/img/logo.png");   
            try {
                MimeMessage mimeMessage = emailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
                String subject = messageSource.getMessage("mail.registration.success.subject", null, locale);
                String html = templateEngine.process("registration-mail.html",ctx);
                String to = toEmail;
                message.setFrom(fromEmail);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(html,true);
//                message.addInline("logo", new ClassPathResource("/static/assets/img/logo.png"), "image/png");
                this.emailSender.send(mimeMessage);			
            } catch (MessagingException me) {
                    System.out.println(me);
            }
	}
        
        /**
         * Permet l'envoi d'un mesage simple avec sujet
         * @param email
         * @param subject
         * @param text 
         */
        public void sendMessage(String email, String subject, String text){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(text);
            this.emailSender.send(message);

        }

}
