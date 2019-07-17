package hr.brocom.recept.model;

public class ProductRecipeDto {
    private Integer quantity;
    private ProductDto product;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductRecipeDto{" + "quantity=" + quantity + ", product=" + product + '}';
    }
}
