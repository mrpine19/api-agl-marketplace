package br.com.fiap.marketPlaceAGL.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartTest {

    private ShoppingCart carrinho;
    private Product produto;

    @BeforeEach
    public void setUp(){
        carrinho = new ShoppingCart();
        carrinho.setProducts(new ArrayList<>());
        produto = new Product();
    }

    @Test
    public void deveAdicionarUmItemAoCarrinho() {
        produto.setProdutoDisponivel(true);
        carrinho.addItemShoppingCart(produto);

        assert(carrinho.getProducts().size() == 1);
    }

    @Test
    public void naoDeveAdicionarUmItemAoCarrinho() {
        produto.setProdutoDisponivel(false);
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> carrinho.addItemShoppingCart(produto));

        assert(carrinho.getProducts().size() == 0);
        assertTrue(ex.getMessage().contains("Este produto não está disponível para ser adicionado ao carrinho."));
    }

    @Test
    public void deveRemoverrUmItemDoCarrinho() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        carrinho.getProducts().add(produtoMock);

        carrinho.removeItemShoppingCart(1L);

        assert(carrinho.getProducts().isEmpty());
    }

    @Test
    public void naoDeveRemoverrUmItemDoCarrinho() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        carrinho.getProducts().add(produtoMock);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> carrinho.removeItemShoppingCart(2L));

        assertTrue(ex.getMessage().contains("Este produto não está no carrinho. Seu burro!"));
    }
}