package com.aplose.aploseframework.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.aploseframework.dto.stripe.CreateCheckoutDto;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.service.PersonService;
import com.aplose.aploseframework.service.ServiceService;
import com.aplose.aploseframework.service.stripe.StripeAccountService;
import com.aplose.aploseframework.service.stripe.StripeCheckoutService;
import com.aplose.aploseframework.service.stripe.StripeCustomerService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/api/stripe")
@CrossOrigin
public class StripeController {

    @Autowired
    private PersonService _personService;
    @Autowired
    private StripeCustomerService _stripeCustomerService;
    @Autowired
    private StripeCheckoutService _stripeCheckoutService;
    @Autowired
    private ServiceService _serviceService;
    @Autowired
    private StripeAccountService _stripeAccountService;
    



    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@AuthenticationPrincipal UserAccount userAccount, @RequestBody CreateCheckoutDto createCheckoutDto){
        // le service 
        // le payeur
        // le paymentMethod
        return ResponseEntity.ok(this._stripeCheckoutService.createPaymentIntent(
            userAccount,
            this._serviceService.getServiceById(createCheckoutDto.getServiceId()),
            createCheckoutDto.getPaymentMethodId()).toJson()
        );
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<String> getAccount(@PathVariable("id") String accountId) throws StripeException{
        return ResponseEntity.ok(this._stripeAccountService.getById(accountId).toJson());
    }


    /*
     * Create Customer
     */
    @PostMapping("/customer")
    public ResponseEntity<String> createCustomerAccount(@AuthenticationPrincipal UserAccount userAccount) throws StripeException{
        return ResponseEntity.ok(this._stripeCustomerService.createCustomer(this._personService.getByUserAccount(userAccount)).getId());
    }


    //TODO rôle professionel uniquement
    /*
     * Resent AccountLink
     */
    @GetMapping("/account-link")
    public ResponseEntity<String> sendAccountLink(@AuthenticationPrincipal UserAccount userAccount){
        this._stripeAccountService.resendAccountLinkByMail(userAccount);
        return ResponseEntity.ok("AccountLink sended");
    }



    // @GetMapping("/infos")
    // public void getInfos(@AuthenticationPrincipal UserAccount userAccount) throws StripeException{
    //     Account account = this._stripeAccountService.getById(
    //         ((ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, userAccount.getDolibarrThirdPartyId())).getStripeAccountId()
    //     );
    // }
}
