package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getProduct(){
        log.info("Listing all products");
        return service.getProduct();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        log.info("Listing product with id: " + id);
        return service.getProductById(id)
                .map((m) -> ResponseEntity.ok(m))
                .orElse(ResponseEntity.notFound().build());
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
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        log.info("Deleting product with id: " + id);
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}