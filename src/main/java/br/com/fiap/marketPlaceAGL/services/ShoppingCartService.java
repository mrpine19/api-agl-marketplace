package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getShoppingCartProducts() {
        return shoppingCartRepository.findAll();
    }
}
