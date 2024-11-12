package fr.aplose.aploseframework.dto.proposal;


public class ProposalLineDTO {
    
    private Integer quantity;
    private Integer productId;
    private Integer product_type; // type: 0=service; 1=product


    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getProduct_type() {
        return product_type;
    }
    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }


    

}
