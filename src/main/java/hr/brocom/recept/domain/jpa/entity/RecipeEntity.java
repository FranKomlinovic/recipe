package hr.brocom.recept.domain.jpa.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipe")
public class RecipeEntity {
    private Long id;
    private String code;
    private String name;
    private String text;
    private LocalDateTime createdTime;
    private Boolean active = true;
    private List<ProductRecipeEntity> productRecipeList;
    private List<ProductEntity> recommendedProducts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "created_time", nullable = false)
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "recipe")
    public List<ProductRecipeEntity> getProductRecipeList() {
        return productRecipeList;
    }

    public void setProductRecipeList(List<ProductRecipeEntity> productRecipeList) {
        this.productRecipeList = productRecipeList;
    }

    @ManyToMany
    @JoinTable(name = "recipe_recommendation", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<ProductEntity> getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(List<ProductEntity> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, text, createdTime, active);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecipeEntity that = (RecipeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name,
                that.name) && Objects.equals(
                text,
                that.text) && Objects.equals(createdTime, that.createdTime) && Objects.equals(active, that.active);
    }
}
