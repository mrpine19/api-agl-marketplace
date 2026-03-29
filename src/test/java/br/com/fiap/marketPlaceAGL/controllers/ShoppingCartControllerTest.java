package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.dto.ResultShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.services.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartService service;

    @InjectMocks
    private ShoppingCartController controller;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void deveRetornarTodosOsCarrinhos() {
        List<ResultShoppingCartDTO> listaMock = List.of(new ResultShoppingCartDTO());
        Mockito.when(service.getAllShoppingCart()).thenReturn(listaMock);

        List<ResultShoppingCartDTO> resultado = controller.getAllShoppingCart();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    public void deveAdicionarUmProdutoAoCarrinho() {
        ShoppingCartDTO dtoMock = new ShoppingCartDTO(1, 1);
        ShoppingCart carrinhoMock = new ShoppingCart();

        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);

        carrinhoMock.setCustomer(clienteMock);

        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        produtoMock.setNomeProduto("Mouse");

        ResultShoppingCartDTO resultDTOMock = new ResultShoppingCartDTO(1, 1, List.of(produtoMock));

        Mockito.when(service.addItemShoppingCart(dtoMock)).thenReturn(resultDTOMock);

        ResponseEntity resultado = controller.addItemShoppingCart(dtoMock);
        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());

        ResultShoppingCartDTO body = (ResultShoppingCartDTO) resultado.getBody();
        assertEquals(1, body.getProducts().size());
        assertEquals("Mouse", body.getProducts().getFirst().getNomeProduto());
    }

    @Test
    public void deveRemoverUmProdutoAoCarrinho() {
        ShoppingCartDTO dtoMock = new ShoppingCartDTO(1, 1);
        ShoppingCart carrinhoMock = new ShoppingCart();

        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);

        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        produtoMock.setNomeProduto("Mouse");

        carrinhoMock.setCustomer(clienteMock);
        carrinhoMock.setProducts(List.of(produtoMock));

        ResultShoppingCartDTO resultDTOMock = new ResultShoppingCartDTO(1, 1, List.of());

        Mockito.when(service.removeItemShoppingCart(dtoMock)).thenReturn(resultDTOMock);

        ResponseEntity resultado = controller.removeItemShoppingCart(dtoMock);
        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());

        ResultShoppingCartDTO body = (ResultShoppingCartDTO) resultado.getBody();
        assertTrue(body.getProducts().isEmpty());
    }

}