package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.dto.ResultShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.dto.ShoppingCartDTO;
import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.Product;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository repository;

    @Mock
    private ProductService produtoService;

    @InjectMocks
    private ShoppingCartService service;

    @Test
    public void deveRetornarTodosOsCarrinhos() {
        Customer cliente = new Customer();
        cliente.setIdCliente(1L);

        ShoppingCart carrinho = new ShoppingCart();
        carrinho.setIdCarrinho(1L);
        carrinho.setCustomer(cliente);
        carrinho.setProducts(List.of());

        List<ShoppingCart> listaMock = List.of(carrinho);

        Mockito.when(repository.findAll()).thenReturn(listaMock);

        List<ResultShoppingCartDTO> resultado = service.getAllShoppingCart();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdCustomer());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void deveRetornarUmCarrinhoPeloIdDoCliente() {
        Customer cliente = new Customer();
        cliente.setIdCliente(1L);

        ShoppingCart carrinho = new ShoppingCart();
        carrinho.setIdCarrinho(1L);
        carrinho.setCustomer(cliente);
        carrinho.setProducts(List.of());

        Mockito.when(repository.getShoppingCartByCustomerId(1L)).thenReturn(Optional.of(carrinho));

        ShoppingCart resultado = service.getShoppingCartByCustomerId(1L);
        assertNotNull(resultado);
    }

    @Test
    public void naoDeveRetornarUmCarrinhoPeloIdDoCliente() {
        Mockito.when(repository.getShoppingCartByCustomerId(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> service.getShoppingCartByCustomerId(1));
        assertTrue(ex.getMessage().contains("Shopping cart not found"));
    }

    @Test
    public void deveCriarUmCarrinho() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1L);

        ShoppingCart carrinhoSalvoNoBanco = new ShoppingCart();
        carrinhoSalvoNoBanco.setIdCarrinho(1L);
        carrinhoSalvoNoBanco.setCustomer(clienteMock);

        Mockito.when(repository.save(Mockito.any(ShoppingCart.class))).thenReturn(carrinhoSalvoNoBanco);

        ShoppingCart resultado = service.createShoppingCart(clienteMock);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCarrinho());
        Mockito.verify(repository).save(Mockito.any(ShoppingCart.class));
    }

    @Test
    public void deveAdicionarUmProdutoAoCarrinho() {
        ShoppingCartDTO carrinhoDTOMock = new ShoppingCartDTO(1,1);

        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        produtoMock.setProdutoDisponivel(true);

        Mockito.when(produtoService.getProductById(1L)).thenReturn(produtoMock);

        Customer cliente = new Customer();
        cliente.setIdCliente(1L);

        ShoppingCart carrinho = new ShoppingCart();
        carrinho.setIdCarrinho(1L);
        carrinho.setCustomer(cliente);
        carrinho.setProducts(new ArrayList<>());

        Mockito.when(repository.getShoppingCartByCustomerId(1L)).thenReturn(Optional.of(carrinho));
        Mockito.when(repository.save(Mockito.any(ShoppingCart.class))).thenReturn(carrinho);

        ResultShoppingCartDTO resultado = service.addItemShoppingCart(carrinhoDTOMock);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCustomer());
        assertEquals(1, resultado.getProducts().size());
    }

    @Test
    public void deveRemoverUmProdutoAoCarrinho() {
        ShoppingCartDTO carrinhoDTOMock = new ShoppingCartDTO(1,1);

        Product produtoMock = new Product();
        produtoMock.setIdProduto(1L);
        produtoMock.setProdutoDisponivel(true);

        List<Product> listaProdutosMock = new ArrayList<>();
        listaProdutosMock.add(produtoMock);

        Customer cliente = new Customer();
        cliente.setIdCliente(1L);

        ShoppingCart carrinho = new ShoppingCart();
        carrinho.setIdCarrinho(1L);
        carrinho.setCustomer(cliente);
        carrinho.setProducts(listaProdutosMock);

        Mockito.when(repository.getShoppingCartByCustomerId(1L)).thenReturn(Optional.of(carrinho));
        Mockito.when(repository.save(Mockito.any(ShoppingCart.class))).thenReturn(carrinho);

        ResultShoppingCartDTO resultado = service.removeItemShoppingCart(carrinhoDTOMock);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCustomer());
        assertEquals(0, resultado.getProducts().size());
    }
}