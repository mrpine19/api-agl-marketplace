package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    public List<ShoppingCart> getShoppingCart() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart getShoppingCartByCustomerId(long id){
        return shoppingCartRepository.getShoppingCartByCustomerId(id);
    }

    public ShoppingCart createShoppingCart(Customer customer){
        return shoppingCartRepository.save(new ShoppingCart(customer));
    }

    public ShoppingCart addItemShoppingCart(ShoppingCartDTO dto) {
        Optional<Product> product = productService.getProductById(dto.getIdProduct());
        //Optional<Customer> customer = customerService.getCustomer(dto.getIdCustomer());

        ShoppingCart shoppingCart = getShoppingCartByCustomerId(dto.getIdCustomer());
        shoppingCart.getProducts().add(product.get());

        return shoppingCartRepository.save(shoppingCart);
    }
}
