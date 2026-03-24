package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
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

    @Autowired
    private CustomerService customerService;

    public List<ShoppingCart> getShoppingCart() {
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> addShoppingCart(ShoppingCartDTO dto) {
        Optional product = productService.getProductById(dto.getIdProduct());
        Optional customer = customerService.getCustomer(dto.getIdCustomer());

    }
}
