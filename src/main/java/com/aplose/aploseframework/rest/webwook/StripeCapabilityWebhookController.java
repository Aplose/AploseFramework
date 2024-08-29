package com.aplose.aploseframework.rest.webwook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.aploseframework.ZDEVELOP.devController;
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
@RequestMapping("/api/webhook/stripe/capability")
public class StripeCapabilityWebhookController {
    
    @Value("${stripe.webhook.secret}")
    private String STRIPE_WEBHOOK_SECRET;
    @Value("${stripe.api.key}")
    private String _stripeApiKey;

    @Autowired
    private StripeAccountService _stripeAccountService;
    @Autowired
    private UserAccountService _userAccountService;



    @PostConstruct
    public void init(){
        Stripe.apiKey = _stripeApiKey;
    }


     @PostMapping()
    public String handleStripeCapability(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, "whsec_f2bbdad3f5659e18b96eacd529b2e8b56a89a45f0202ecf063b1db70582249f8");
            
        } catch (Exception e) {
            System.out.println("\n\n Webhook ERROR:");
            System.err.println("webhook signature" +STRIPE_WEBHOOK_SECRET);
            System.err.println("sig header: " + sigHeader);
            return "Webhook capability error: " + e.getMessage();
        }

        switch (event.getType()) {


            /*
             * Occurs whenever a capability has new requirements or a new status.
             */
            case "capability.updated": {
                System.out.println("\n\n\t weebhook: capability.updated");
                try{
                    Account account = this._stripeAccountService.getById(event.getAccount());
                    UserAccount userAccount = this._userAccountService.loadUserByUsername(account.getEmail());
                    String transferCapability = account.getCapabilities().getTransfers();
                    String cardPaymentCapability = account.getCapabilities().getCardPayments();
                    System.err.println("\nTRANSFER: " + transferCapability);
                    System.err.println("CARDPAYMENT: : " + cardPaymentCapability + "\n");

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

                      developHelper.printObject(userAccount, "webhook capability AVANT SAVE");
                      userAccount = this._userAccountService.save(userAccount);
                      developHelper.printObject(userAccount, "webhook capability APRES SAVE");
                }
                catch(StripeException e){
                    return "fail";
                }
                break;
            }

            default:
              System.out.println("Capability - Unhandled event type: " + event.getType());
        }

        return "";
    }
}
