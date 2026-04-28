package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Page<Product> findAllProducts(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Product addProduct(Product product){
        return repository.save(product);
    }

    public Product getProductById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Page<Product> findProductById(Long id, Pageable pageable){
        return repository.findProductByIdProduto(id, pageable);
    }

    public Page<Product> findByPrecoProdutoMenorQue(float precoProduto, Pageable pageable){
        return repository.findByPrecoProdutoLessThanEqual(precoProduto, pageable);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        product.setProdutoDisponivel(false);
        repository.save(product);
    }

    public Product updateProduct(Long id, Product newProduct) {
        getProductById(id);
        newProduct.setIdProduto(id);
        return repository.save(newProduct);
    }
}