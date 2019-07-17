package hr.brocom.recept.controller;

import hr.brocom.recept.model.ProductDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("")
    public RestDto<Void> addProduct(@RequestBody @Valid ProductDto productDto) {
        log.info("Adding product: {}...", productDto.getCode());
        log.trace("Product: {}", productDto);
        long time = System.currentTimeMillis();
        productService.addProduct(productDto);
        log.trace("addProduct finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Product uspješno dodan!");
    }

    @PutMapping("")
    public RestDto<Void> updateProduct(@RequestBody ProductDto productDto) {
        log.info("Update product: {}...", productDto.getCode());
        log.trace("Product: " + productDto);
        long time = System.currentTimeMillis();
        productService.updateProduct(productDto);
        log.trace("updateProduct finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Product uspješno ažuriran");
    }

    @DeleteMapping("/{code}")
    public RestDto<Void> deactivateProduct(@PathVariable String code) {
        log.info("Deleting product: {}...", code);
        long time = System.currentTimeMillis();
        productService.deactivateProduct(code);
        log.trace("deleteProduct finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Product uspješno obrisan");
    }
}
