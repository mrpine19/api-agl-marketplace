package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.dto.ResultShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    public List<ResultShoppingCartDTO> getAllShoppingCart() {
        return shoppingCartRepository.findAll()
                .stream()
                .map(shoppingCart -> convertDTO(shoppingCart))
                .toList();
    }

    public ShoppingCart getShoppingCartByCustomerId(long id){
        return shoppingCartRepository.getShoppingCartByCustomerId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shopping cart not found"));
    }

    public ShoppingCart createShoppingCart(Customer customer){
        return shoppingCartRepository.save(new ShoppingCart(customer));
    }

    public ResultShoppingCartDTO addItemShoppingCart(ShoppingCartDTO dto) {
        Optional<Product> product = productService.getProductById(dto.getIdProduct());

        ShoppingCart shoppingCart = getShoppingCartByCustomerId(dto.getIdCustomer());
        shoppingCart.addItemShoppingCart(product.get());

        shoppingCartRepository.save(shoppingCart);

        return convertDTO(shoppingCart);
    }

    public ResultShoppingCartDTO removeItemShoppingCart(ShoppingCartDTO dto) {
        ShoppingCart shoppingCart = getShoppingCartByCustomerId(dto.getIdCustomer());

        shoppingCart.removeItemShoppingCart(dto.getIdProduct());

        shoppingCartRepository.save(shoppingCart);

        return convertDTO(shoppingCart);
    }

    private ResultShoppingCartDTO convertDTO(ShoppingCart shoppingCart) {
        return new ResultShoppingCartDTO(
                shoppingCart.getIdCarrinho(),
                shoppingCart.getCustomer().getIdCliente(),
                shoppingCart.getProducts()
        );
    }
}
