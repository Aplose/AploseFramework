package com.aplose.aploseframework.rest.webwook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.service.UserAccountService;
import com.aplose.aploseframework.service.stripe.StripeAccountService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin
@RequestMapping("/api/webhook/stripe/account")
public class StripeAccountWebhookController {

    @Value("${stripe.webhook.secret}")
    private String STRIPE_WEBHOOK_SECRET;
    @Value("${stripe.api.key}")
    private String _stripeApiKey;

    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private StripeAccountService _stripeAccountService;


    @PostConstruct
    public void init(){
        Stripe.apiKey = _stripeApiKey;
    }


    @PostMapping()
    public ResponseEntity<String> handleStripeAccount(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, "whsec_f2bbdad3f5659e18b96eacd529b2e8b56a89a45f0202ecf063b1db70582249f8");
            
        } catch (Exception e) {
            System.out.println("\n\n Webhook ERROR:");
            System.err.println("webhook signature" +STRIPE_WEBHOOK_SECRET);
            System.err.println("sig header: " + sigHeader);
            return ResponseEntity.badRequest().body("Webhook error: " + e.getMessage());
        }

        // System.err.println("\n\n\n\t Payload" + payload);
        // System.err.println("\n\n\n\t event" + event);

        switch (event.getType()) {

          /*
           * Occurs whenever an account status or property has changed.
           */
            case "account.updated": {
              System.out.println("\n\n\t weebhook: account.updated");

              
              Account account;
              try{
                account = this._stripeAccountService.getById(event.getAccount());
              }
              catch(StripeException e){
                System.out.println("\n\n\t ERROR at webhook.stripe.account.updated: " + e.getMessage());
                return ResponseEntity.badRequest().body("ERROR at webhook.stripe.account.updated: " + e.getMessage());
              }
              developHelper.printObject(account, null);
              
              UserAccount userAccount = this._userAccountService.loadUserByUsername(account.getEmail());
              developHelper.printObject(userAccount, null);

              if(account.getCapabilities().getTransfers().equals("active")){
                userAccount.setStripeTransferIsEnabled(true);
              }else{
                userAccount.setStripeTransferIsEnabled(false);
              }
              if(account.getCapabilities().getCardPayments().equals("active")){
                userAccount.setStripCardPaymentIsEnabled(true);
              }else{
                userAccount.setStripCardPaymentIsEnabled(false);
              }
              this._userAccountService.save(userAccount);

              break;
            }


            /*
             * Occurs whenever a user authorizes an application. Sent to the related application only.
             */
            case "account.application.authorized": {
              System.out.println("\n\n\t weebhook: account.application.authorized");

              try{
                UserAccount userAccount = this._userAccountService.loadUserByUsername(this._stripeAccountService.getById(event.getAccount()).getEmail());
                userAccount.setStripeApplicationIsAuthorized(true);
                this._userAccountService.save(userAccount);
              }catch(StripeException e){

                return ResponseEntity.badRequest().body("weebhook: account.application.authorized" + e.getMessage());
              }

              
              break;
            }


            /*
             * Occurs whenever a user deauthorizes an application. Sent to the related application only.
             */
            case "account.application.deauthorized": {
              System.out.println("\n\n\t weebhook: account.application.deauthorized");
              // UserAccount userAccount = this._userAccountService.getByStripeLinkedAccountId(event.getAccount());
              // userAccount.setStripeApplicationIsAuthorized(false);
              // this._userAccountService.save(userAccount);
              break;
            }


            /*
             * Occurs whenever an external account is created.
             */
            case "account.external_account.created": {
              System.out.println("\n\n\t weebhook: account.external_account.created");
              System.err.println("\n\t Linked account id: " + event.getAccount());
              // UserAccount userAccount = this._userAccountService.getByStripeLinkedAccountId(event.getAccount());
              // userAccount.setStripeExternalAccountIsCreated(true);
              // this._userAccountService.save(userAccount);
              break;
            }


            /*
             * Occurs whenever an external account is deleted.
             */
            case "account.external_account.deleted": {
              System.out.println("\n\n\t weebhook: account.external_account.deleted");
              // UserAccount userAccount = this._userAccountService.getByStripeLinkedAccountId(event.getAccount());
              // userAccount.setStripeExternalAccountIsCreated(false);
              // this._userAccountService.save(userAccount);
              break;
            }


            /*
             * Occurs whenever an external account is updated.
             */
            case "account.external_account.updated": {
              System.out.println("\n\n\t weebhook: account.external_account.updated");
              break;
            }

            default:
              System.out.println("Unhandled event type at end of account.updated: " + event.getType());
          }

        // System.err.println("hook" );
        // developHelper.printObject(event, null);

        return ResponseEntity.ok("Success");
    }


}

