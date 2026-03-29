package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product addProduct(Product product){
        return repository.save(product);
    }

    public Product getProductById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public void deleteProduct(Long id) {
        getProductById(id);
        repository.deleteById(id);
    }

    public Product updateProduct(Long id, Product newProduct) {
        getProductById(id);
        newProduct.setIdProduto(id);
        return repository.save(newProduct);
    }
}