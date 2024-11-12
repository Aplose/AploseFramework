package fr.aplose.aploseframework.rest.webwook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.service.ConfigService;
import fr.aplose.aploseframework.service.UserAccountService;
import fr.aplose.aploseframework.service.stripe.StripeAccountService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/webhook/stripe/capability")
public class StripeCapabilityWebhookController {

    private String stripeWebHookSecret;

    @Autowired
    private StripeAccountService _stripeAccountService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private ConfigService configService;

    @PostConstruct
    public void init() {
        stripeWebHookSecret = configService.getStringConfig("stripe.webhook.capability.secretkey");
        Stripe.apiKey = configService.getStringConfig("stripe.api.key");
    }

    @PostMapping()
    @Async
    public ResponseEntity<String> handleStripeCapability(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, this.stripeWebHookSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.badRequest().body("SignatureVerificationException: " + e.getMessage());
        }

        switch (event.getType()) {

            /*
             * Occurs whenever a capability has new requirements or a new status.
             */
            case "capability.updated": {
                try {
                    Account account = this._stripeAccountService.getById(event.getAccount());
                    UserAccount userAccount = this._userAccountService.loadUserByUsername(account.getEmail());

                    if (account.getCapabilities().getTransfers().equals("active")) {
                        userAccount.setStripeTransferIsEnabled(true);
                    } else {
                        userAccount.setStripeTransferIsEnabled(false);
                    }
                    if (account.getCapabilities().getCardPayments().equals("active")) {
                        userAccount.setStripCardPaymentIsEnabled(true);
                    } else {
                        userAccount.setStripCardPaymentIsEnabled(false);
                    }

                    userAccount = this._userAccountService.update(userAccount);
                } 
                catch (StripeException e) {
                    return ResponseEntity.badRequest().body("StripeException: " + e.getMessage());
                } 
                catch (UsernameNotFoundException e) {
                    return ResponseEntity.badRequest().body("UsernameNotFoundException: " + e.getMessage());
                }
                break;
            }

            default:
                System.out.println("Capability - Unhandled event type: " + event.getType());
        }
        return ResponseEntity.ok("success");
    }
}
