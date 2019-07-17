package hr.brocom.recept.service;

import hr.brocom.recept.domain.jpa.ProductJpaImpl;
import hr.brocom.recept.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductJpaImpl productJpa;

    public List<ProductDto> getAllProducts() {
        return productJpa.getAllProducts();
    }

    public void addProduct(ProductDto productDto) {
        productJpa.addProduct(productDto);
    }

    public void updateProduct(ProductDto productDto) {
        productJpa.updateProduct(productDto);
    }

    public void deactivateProduct(String code) {
        productJpa.deactivateProduct(code);
    }
}
