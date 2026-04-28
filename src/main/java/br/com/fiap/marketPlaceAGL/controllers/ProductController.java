package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.dto.ProductRequest;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ProductService service;

    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable){
        log.info("Listing all products");
        return service.findAllProducts(pageable);
    }

    @GetMapping("{id}")
    public Page<Product> getProductById(@PathVariable Long id, Pageable pageable){
        log.info("Listing product with id: " + id);
        return service.findProductById(id, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid ProductRequest product){
        log.info("Registering product: " + product);
        return service.addProduct(product.toEntity());
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest newProduct){
        log.info("Updating product with id: " + id);
        Product product = service.updateProduct(id, newProduct.toEntity());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        log.info("Deleting product with id: " + id);
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}