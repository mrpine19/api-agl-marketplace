package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.services.ProductService;
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
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        log.info("Listing product with id: " + id);
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        log.info("Registering product: " + product);
        var newProduct = service.addProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        log.info("Updating product with id: " + id);
        Product product = service.updateProduct(id, newProduct);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        log.info("Deleting product with id: " + id);
        return ResponseEntity.ok(service.deleteProduct(id));
    }

}