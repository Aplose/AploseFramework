package com.aplose.aploseframework.dto.stripe;


public class CreateCheckoutDto {

    private Long serviceId;
    private String paymentMethodId;
    
    
 

    public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    public String getPaymentMethodId() {
        return paymentMethodId;
    }
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }        
}
