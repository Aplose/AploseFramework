package com.aplose.aploseframework.rest.webwook;

import com.aplose.aploseframework.service.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/webhook/stripe/identity")
@CrossOrigin
public class StripeIdentityWebhookController {

    @Autowired
    private ConfigService configService;
    
    private String stripeWebHookSecret;


    @PostConstruct
    public void init(){
        stripeWebHookSecret = configService.getStringConfig("stripe.webhook.secret");
        Stripe.apiKey = configService.getStringConfig("stripe.api.key");
    }



    @PostMapping()
    public String handleStripeIdentityVerification(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, this.stripeWebHookSecret);
            
        } catch (Exception e) {
            System.out.println("\n\n Webhook ERROR:");
            System.err.println("webhook signature" +stripeWebHookSecret);
            System.err.println("sig header: " + sigHeader);
            return "Webhook error: " + e.getMessage();
        }


        /*
         * Occurs whenever a VerificationSession is canceled
         */
        switch (event.getType()) {
            case "identity.verification_session.canceled": {
                System.out.println("\n\n\t weebhook: identity.verification_session.canceled");
                break;
            }


            /*
             * Occurs whenever a VerificationSession is created
             */
            case "identity.verification_session.created": {
                System.out.println("\n\n\t weebhook: identity.verification_session.created");
                break;
            }


            /*
             * Occurs whenever a VerificationSession transitions to processing
             */
            case "identity.verification_session.processing": {
                System.out.println("\n\n\t weebhook: identity.verification_session.processing");
                break;
            }


            /*
             * Occurs whenever a VerificationSession is redacted. You must create a webhook endpoint which explicitly subscribes to this event 
             * type to access it. Webhook endpoints which subscribe to all events will not include this event type.
             */
            case "identity.verification_session.redacted": {
                System.out.println("\n\n\t weebhook: identity.verification_session.redacted");
                break;
            }


            /*
             * Occurs whenever a VerificationSession transitions to require user input
             */
            case "identity.verification_session.requires_input": {
                System.out.println("\n\n\t weebhook: identity.verification_session.requires_input");
                // UserAccount userAccount = this._userAccountService.getByStripeLinkedAccountId(event.getAccount());
                // userAccount.setStripeLinkedAccountIsVerified(false);
                // userAccount.setStripeLinkedAccountVerificationRequireInput(true);
                // this._userAccountService.save(userAccount);
                break;
            }


            /* 
             * Occurs whenever a VerificationSession transitions to verified
             */
            case "identity.verification_session.verified": {
                System.out.println("\n\n\t weebhook: identity.verification_session.verified");
                // UserAccount userAccount = this._userAccountService.getByStripeLinkedAccountId(event.getAccount());
                // userAccount.setStripeLinkedAccountIsVerified(true);
                // userAccount.setStripeLinkedAccountVerificationRequireInput(false);
                // this._userAccountService.save(userAccount);
                break;
            }
            default:
                System.out.println("Unhandled event type: " + event.getType());
          }
        return "Success";
        }
}
