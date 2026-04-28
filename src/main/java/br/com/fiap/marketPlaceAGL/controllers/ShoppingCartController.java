package br.com.fiap.marketPlaceAGL.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.marketPlaceAGL.dto.ResultShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.services.ShoppingCartService;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<ResultShoppingCartDTO> getAllShoppingCart(){
        log.info("Buscando todos os carrinhos");
        return shoppingCartService.getAllShoppingCart();
    }

    @PutMapping
    public ResponseEntity<ResultShoppingCartDTO> addItemShoppingCart(@RequestBody ShoppingCartDTO dto){
        log.info("Adicionando um novo produto ao carrinho do cliente " + dto.getIdCustomer());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(shoppingCartService.addItemShoppingCart(dto));
    }

    @DeleteMapping
    public ResponseEntity<ResultShoppingCartDTO> removeItemShoppingCart(@RequestBody ShoppingCartDTO dto){
        log.info("Removendo o produto de id " + dto.getIdProduct() + " ao carrinho do cliente " + dto.getIdCustomer());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(shoppingCartService.removeItemShoppingCart(dto));
    }

    @GetMapping("products/{productId}")
    public Page<ShoppingCart> findByIdProduto(@PathVariable Long productId, Pageable pageable){
        log.info("Buscando todos os carrinhos que contêm o produto de Id " + productId);

        return shoppingCartService.findByIdProduto(productId, pageable);
    }


}
