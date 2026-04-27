package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

//    @Test
//    public void deveRetornarTodosOsProdutos() {
//        List<Product> listaMock = List.of(new Product());
//        Mockito.when(service.getAllProducts()).thenReturn(listaMock);
//
//        List<Product> resultado = service.getAllProducts();
//        assertNotNull(resultado);
//        assertEquals(1, resultado.size());
//        Mockito.verify(repository, Mockito.times(1)).findAll();
//    }

    @Test
    public void deveRetornarUmProduto() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(produtoMock));

        Product resultado = service.getProductById(1L);
        assertNotNull(resultado);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void naoDeveRetornarUmProduto() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex =assertThrows(ResponseStatusException.class, () -> service.getProductById(1L));
        assertTrue(ex.getMessage().contains("Product not found"));
    }

    @Test
    public void deveAdicionarUmProduto() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        Mockito.when(repository.save(produtoMock)).thenReturn(produtoMock);

        Product resultado = service.addProduct(produtoMock);
        assertNotNull(resultado);
    }

    @Test
    public void deveAtualizarUmProduto() {
        Product produtoOriginal = new Product();
        produtoOriginal.setIdProduto(1L);
        produtoOriginal.setPrecoProduto(54.99F);

        Product produtoAtualizado = new Product();
        produtoAtualizado.setPrecoProduto(104.99F);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(produtoOriginal));
        Mockito.when(repository.save(produtoAtualizado)).thenReturn(produtoAtualizado);

        Product resultado = service.updateProduct(1L, produtoAtualizado);
        assertNotNull(resultado);
        assertNotEquals(54.99F, resultado.getPrecoProduto());
        assertEquals(104.99F, resultado.getPrecoProduto());
    }

    @Test
    public void deveDeletarUmCliente() {
        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        produtoMock.setProdutoDisponivel(true);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(produtoMock));
        Mockito.when(repository.save(produtoMock)).thenReturn(produtoMock);

        Product resultado = service.deleteProduct(1L);
        assertNotNull(resultado);
        assertFalse(resultado.isProdutoDisponivel());
    }

}