package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.dto.ResultShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    List<ResultShoppingCartDTO> listResultDTO;

    public List<ResultShoppingCartDTO> getShoppingCart() {
        listResultDTO = new ArrayList<>();
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();

        for (ShoppingCart shoppingCart : shoppingCartList){
            converteDTO(shoppingCart);
        }

        return listResultDTO;
    }

    private void converteDTO(ShoppingCart shoppingCart) {
        listResultDTO.add(new ResultShoppingCartDTO(
                shoppingCart.getIdCarrinho(),
                shoppingCart.getCustomer().getIdCliente(),
                shoppingCart.getProducts()
        ));
    }

    public ShoppingCart getShoppingCartByCustomerId(long id){
        return shoppingCartRepository.getShoppingCartByCustomerId(id);
    }

    public ShoppingCart createShoppingCart(Customer customer){
        return shoppingCartRepository.save(new ShoppingCart(customer));
    }

    public ResultShoppingCartDTO addItemShoppingCart(ShoppingCartDTO dto) {
        Optional<Product> product = productService.getProductById(dto.getIdProduct());
        //Optional<Customer> customer = customerService.getCustomer(dto.getIdCustomer());

        ShoppingCart shoppingCart = getShoppingCartByCustomerId(dto.getIdCustomer());
        shoppingCart.getProducts().add(product.get());

        shoppingCartRepository.save(shoppingCart);

        return new ResultShoppingCartDTO(
                shoppingCart.getIdCarrinho(),
                shoppingCart.getCustomer().getIdCliente(),
                shoppingCart.getProducts());
    }
}
