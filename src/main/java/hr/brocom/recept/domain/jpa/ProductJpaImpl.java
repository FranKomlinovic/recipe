package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.entity.ProductEntity;
import hr.brocom.recept.domain.jpa.repository.ProductRepository;
import hr.brocom.recept.model.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class ProductJpaImpl {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addProduct(ProductDto productDto) {
        modifyProduct(new ProductEntity(), productDto);
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        modifyProduct(findByCode(productDto.getCode()), productDto);
    }

    @Transactional
    public void deactivateProduct(String code) {
        ProductEntity productEntity = findByCode(code);
        productEntity.setActive(false);
        productRepository.saveAndFlush(productEntity);
    }

    @Transactional
    public ProductDto findProductByCode(String code) {
        return modelMapper.map(findByCode(code), ProductDto.class);
    }

    private ProductEntity findByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    private void modifyProduct(ProductEntity productEntity, ProductDto productDto) {
        productEntity.setCode(productDto.getCode());
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productRepository.saveAndFlush(productEntity);

        productRepository.saveAndFlush(productEntity);
    }
}
