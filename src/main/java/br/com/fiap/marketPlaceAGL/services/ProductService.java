package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProduct(){
        return repository.findAll();
    }

    public Product addProduct(Product product){
        return repository.save(product);
    }

    public Optional<Product> getProductById(Long id){
        Optional<Product> product = repository.findById(id);
        checkIfProductExists(product);
        return product;
    }

    public void deleteProduct(Long id) {
        var optionalProduct = getProductById(id);
        checkIfProductExists(optionalProduct);
        repository.deleteById(id);
    }

    public Product updateProduct(Long id, Product newProduct) {
        var optionalProduct = getProductById(id);
        checkIfProductExists(optionalProduct);
        newProduct.setIdProduto(id);
        return repository.save(newProduct);
    }

    public void checkIfProductExists(Optional<Product> product){
        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
}