package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.dto.ResultShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.services.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ResultShoppingCartDTO> getShoppingCart(){
        log.info("Buscando todos os carrinhos");
        return shoppingCartService.getShoppingCart();
    }

    @PutMapping
    public ResponseEntity<ResultShoppingCartDTO> addItemShoppingCart(@RequestBody ShoppingCartDTO dto){
        log.info("Adicionando um novo produto ao carrinho do cliente " + dto.getIdCustomer());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(shoppingCartService.addItemShoppingCart(dto));
    }

}
