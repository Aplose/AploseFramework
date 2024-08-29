package com.aplose.aploseframework.service.stripe;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.model.Service;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.dolibarr.ThirdParty;
import com.aplose.aploseframework.service.DolibarrService;
import com.aplose.aploseframework.service.PersonService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;

@org.springframework.stereotype.Service
public class StripeCheckoutService {

    @Autowired
    private DolibarrService _dolibarrService;
    @Autowired
    private StripeCustomerService _stripeCustomerService;
    @Autowired
    private PersonService _personService;


    // Créer une intention de payement
    public PaymentIntent createPaymentIntent(UserAccount userAccount, Service service, String paymentMethodId){

        ThirdParty professionalThirdParty = (ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, service.getProfessional().getUserAccount().getDolibarrThirdPartyId());
        ThirdParty customerThirdParty = (ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, userAccount.getDolibarrThirdPartyId());

        if(customerThirdParty.getStripeCustomerId() == null || customerThirdParty.getStripeCustomerId().equals("")){
            this._stripeCustomerService.createCustomer(this._personService.getByUserAccount(userAccount));
            customerThirdParty = (ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, userAccount.getDolibarrThirdPartyId());
        }

        developHelper.printObject(service.getProfessional(), null);
        developHelper.printObject(professionalThirdParty, "createPaymentIntent()");
        try{
            
            
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(service.getPrice())
                // .setAmount(Long.valueOf(service.getPrice().toString()) * 100)
                .setCurrency("eur")
                .setCustomer(customerThirdParty.getStripeCustomerId()) // definir le payeur
                .setTransferData(
                    PaymentIntentCreateParams.TransferData.builder()
                    // .setDestination(service.getProfessional().getUserAccount().getStripeLinkedAccountId())
                    .setDestination(professionalThirdParty.getStripeAccountId()) // definir le prestataire qui recevra l'argent
                    .build()
                )
                .setPaymentMethod(paymentMethodId) // le paymentMethodId vient du front lorsque l'utilisateur à renseigné sa carte
                // .setApplicationFeeAmount(0L)    // commission de l'application
                .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            
            developHelper.printObject(paymentIntent, null);
    
            return paymentIntent;
        }
        catch(StripeException e){
            System.err.println( "\n\n\tStripeException - StripeCheckoutService.createPaymentIntent(): " + e.getMessage());
            return null;
        }
    }
}
