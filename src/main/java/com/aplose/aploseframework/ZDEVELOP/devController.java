package com.aplose.aploseframework.ZDEVELOP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.aploseframework.service.stripe.StripeAccountService;
import com.stripe.exception.StripeException;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/dev")
public class devController {
    
    @Autowired
    private StripeAccountService _stripeAccountService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws StripeException{
        System.err.println("\n\nID: " + id);
        this._stripeAccountService.deleteLinkedAccount(id);
    }
}
