package hr.brocom.recept.model;

import java.time.LocalDateTime;
import java.util.List;

public class RecipeDto {
    private String                 code;
    private String                 name;
    private String                 text;
    private LocalDateTime          createdTime;
    private List<ProductRecipeDto> productRecipeList;
    private List<ProductDto>       recommendedProducts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public List<ProductRecipeDto> getProductRecipeList() {
        return productRecipeList;
    }

    public void setProductRecipeList(List<ProductRecipeDto> productRecipeList) {
        this.productRecipeList = productRecipeList;
    }

    public List<ProductDto> getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(List<ProductDto> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }

    @Override
    public String toString() {
        return "RecipeDto{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", text='" + text + '\'' + ", createdTime=" + createdTime + ", productRecipeList=" + productRecipeList + ", recommendedProducts=" + recommendedProducts + '}';
    }
}
