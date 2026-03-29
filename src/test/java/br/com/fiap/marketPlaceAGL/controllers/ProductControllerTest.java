package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.services.ProductService;
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
class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    @Test
    public void deveRetornarTodosOsProdutos() {
        List<Product> listaMock = List.of(new Product());
        Mockito.when(service.getAllProducts()).thenReturn(listaMock);

        List<Product> resultado = controller.getAllProducts();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    public void deveRetornarUmProduto() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        Mockito.when(service.getProductById(1L)).thenReturn(produtoMock);

        ResponseEntity resultado = controller.getProductById(1L);
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    public void deveAdicionarUmCliente() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        Mockito.when(service.addProduct(produtoMock)).thenReturn(produtoMock);

        ResponseEntity resultado = controller.addProduct(produtoMock);
        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }

    @Test
    public void deveAtualizarUmCliente() {
        Product produtoOriginal = new Product();
        produtoOriginal.setIdProduto(1L);
        produtoOriginal.setPrecoProduto(54.99F);

        Product produtoAtualizado = new Product();
        produtoAtualizado.setPrecoProduto(104.99F);

        Mockito.when(service.updateProduct(1L, produtoAtualizado)).thenReturn(produtoAtualizado);

        ResponseEntity resultado = controller.updateProduct(1L, produtoAtualizado);
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        Product body = (Product) resultado.getBody();
        assertNotEquals(54.99F, body.getPrecoProduto());
        assertEquals(104.99F, body.getPrecoProduto());
    }

    @Test
    public void deveDeletarUmCliente() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);

        Mockito.when(service.deleteProduct(1L)).thenReturn(produtoMock);

        ResponseEntity resultado = controller.deleteProduct(1L);
        assertNotNull(resultado);
        assertFalse(produtoMock.isProdutoDisponivel());
    }

}