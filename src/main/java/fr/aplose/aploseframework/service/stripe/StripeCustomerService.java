package fr.aplose.aploseframework.service.stripe;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;

import fr.aplose.aploseframework.model.Person;
import fr.aplose.aploseframework.model.dolibarr.ThirdParty;
import fr.aplose.aploseframework.service.DolibarrService;


@Service
public class StripeCustomerService {
    
    @Autowired
    private DolibarrService _dolibarrService;


    public Customer createCustomer(Person person){

        ThirdParty thirdParty = (ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, person.getUserAccount().getDolibarrThirdPartyId());
        Customer customer;
        try{
            customer = Customer.create(
                CustomerCreateParams.builder()
                .setAddress(
                    CustomerCreateParams.Address.builder()
                    .setCity(person.getAddress().getTown() != null ? person.getAddress().getTown().getName() : null)
                    .setCountry(person.getAddress().getCountry().getLabel())
                    .setCountry("France")
                    .setLine1(person.getAddress().getRow2() != null ? person.getAddress().getRow2() : null)
                    .setLine2(person.getAddress().getRow3() != null ? person.getAddress().getRow3() : null)
                    .setPostalCode(person.getAddress().getTown() != null ? person.getAddress().getTown().getZipCode() : null)
                    .build())
                    .setEmail(person.getUserAccount().getUsername())
                    .setName(person.getFullName())
                    .setPhone(person.getPhone())
                    .setMetadata(new HashMap<String, String>(){{ put("userAccountId", person.getUserAccount().getId().toString()); }})
                    // .setPaymentMethod()  // lorsque l'utilisateur renseignera les informations de sa carte
                    .build()
                    );
        }
        catch(StripeException e){
            System.err.println( "\n\n\tStripeException - StripeController.createCustomer(): " + e.getMessage());
            customer = null;
        }
                
        thirdParty.setStripeCustomerId(customer.getId());
        this._dolibarrService.update(ThirdParty.NAME, thirdParty.getId(), thirdParty);

        return customer;
    }



    public Customer getCustomerById(String customerId) throws StripeException{
        return Customer.retrieve(customerId);
    }



    public void updateCustomer(String customerId) throws StripeException{
        Customer customer = this.getCustomerById(customerId);
        customer.update(
            CustomerUpdateParams.builder().build()
        );
    }



    public Customer deleteCustomer(String customerId) throws StripeException{
        return this.getCustomerById(customerId).delete();
    }
}
