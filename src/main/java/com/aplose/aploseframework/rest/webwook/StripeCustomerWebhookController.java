package com.aplose.aploseframework.rest.webwook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.service.ConfigService;
import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/webhook/stripe/customer")
@CrossOrigin
public class StripeCustomerWebhookController {
    @Autowired
    private ConfigService configService;
    
    private String stripeWebHookSecret;


    @PostConstruct
    public void init(){
        stripeWebHookSecret = configService.getStringConfig("stripe.webhook.secret");
        Stripe.apiKey = configService.getStringConfig("stripe.api.key");
    }
    

    @PostMapping()
    public String handleCustomerWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, this.stripeWebHookSecret);
            
        } catch (Exception e) {
            System.out.println("\n\n Webhook ERROR: " + e.getMessage());
            System.err.println("\n\nwebhook signature" + stripeWebHookSecret);
            System.err.println("sig header: " + sigHeader);
            return "Webhook error: " + e.getMessage();
        }


        switch (event.getType()) {


            /*
             * Occurs whenever a new customer is created.
             */
            case "customer.created": {
                System.err.println("customer.created");
                System.err.println("event: " );
                developHelper.printObject(event,null);
                break;
            }


            /*
             * Occurs whenever a customer is deleted.
             */
            case "customer.deleted": {
                break;
            }


            /*
             * Occurs whenever any property of a customer changes.
             */
            case "customer.updated": {
                break;
            }


            /*
             * Occurs whenever a coupon is attached to a customer.
             */
            case "customer.discount.created": {
                break;
            }


            /*
             * Occurs whenever a coupon is removed from a customer.
             */
            case "customer.discount.deleted": {
                break;
            }


            /*
             * Occurs whenever a customer is switched from one coupon to another.
             */
            case "customer.discount.updated": {
                break;
            }


            /*
             * Occurs whenever a new source is created for a customer.
             */
            case "customer.source.created": {
                break;
            }


            /*
             * Occurs whenever a source is removed from a customer.
             */
            case "customer.source.deleted": {
                break;
            }


            /*
             * Occurs whenever a card or source will expire at the end of the month. This event only works with legacy integrations 
             * using Card or Source objects. If you use the PaymentMethod API, this event won't occur.
             */
            case "customer.source.expiring": {
                break;
            }


            /*
             * Occurs whenever a source's details are changed.
             */
            case "customer.source.updated": {
                break;
            }


            /*
             * Occurs whenever a customer is signed up for a new plan.
             */
            case "customer.subscription.created": {
                break;
            }


            /*
             * Occurs whenever a customer's subscription ends.
             */
            case "customer.subscription.deleted": {
                break;
            }


            /*
             * Occurs whenever a customer's subscription is paused. Only applies when subscriptions enter `status=paused`, 
             * not when [payment collection](https://docs.stripe.com/billing/subscriptions/pause) is paused.
             */
            case "customer.subscription.paused": {
                break;
            }


            /*
             * Occurs whenever a customer's subscription's pending update is applied, and the subscription is updated.
             */
            case "customer.subscription.pending_update_applied": {
                break;
            }


            /*
             * Occurs whenever a customer's subscription's pending update expires before the related invoice is paid.
             */
            case "customer.subscription.pending_update_expired": {
                break;
            }


            /*
             * Occurs whenever a customer's subscription is no longer paused. Only applies when a `status=paused` subscription 
             * is [resumed](https://docs.stripe.com/api/subscriptions/resume), 
             * not when [payment collection](https://docs.stripe.com/billing/subscriptions/pause) is resumed.
             */
            case "customer.subscription.resumed": {
                break;
            }


            /*
             * Occurs three days before a subscription's trial period is scheduled to end, or when a trial is ended immediately (using `trial_end=now`).
             */
            case "customer.subscription.trial_will_end": {
                break;
            }


            /*
             * Occurs whenever a subscription changes (e.g., switching from one plan to another, or changing the status from trial to active).
             */
            case "customer.subscription.updated": {
                break;
            }


            /*
             * Occurs whenever a tax ID is created for a customer.
             */
            case "customer.tax_id.created": {
                break;
            }


            /*
             * Occurs whenever a tax ID is deleted from a customer.
             */
            case "customer.tax_id.deleted": {
                break;
            }


            /*
             * Occurs whenever a customer's tax ID is updated.
             */
            case "customer.tax_id.updated": {
                break;
            }


            default:
                System.out.println("Unhandled event type: " + event.getType());
        }

        return "Success";
    }
}
