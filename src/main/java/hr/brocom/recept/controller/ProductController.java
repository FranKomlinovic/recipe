package hr.brocom.recept.controller;

import hr.brocom.recept.model.ProductDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger         log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private              ProductService productService;

    @GetMapping("/list")
    public RestDto<List<ProductDto>> getAllActiveProducts() {
        log.trace("Get all active Products");
        long time = System.currentTimeMillis();
        List<ProductDto> products = productService.getAllProducts();
        log.trace("getAllActiveProducts finished in {} ms", System.currentTimeMillis() - time);
        log.trace("getAllActiveProducts returned {} results", products.size());
        return RestDto.success(products);
    }
}
