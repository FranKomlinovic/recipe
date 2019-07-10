package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.repository.ProductRepository;
import hr.brocom.recept.model.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
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
}
