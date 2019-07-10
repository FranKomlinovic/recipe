package hr.brocom.recept.model;

import hr.brocom.recept.domain.jpa.entity.ProductEntity;

public class ProductRecipeDto {
    private Integer       quantity;
    private ProductEntity product;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductRecipeDto{" + "quantity=" + quantity + ", product=" + product + '}';
    }
}
