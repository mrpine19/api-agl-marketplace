package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.CustomerRepository;
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
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private ShoppingCartService carrinhoService;

    @InjectMocks
    private CustomerService service;

    @Test
    public void deveRetornarTodosOsCliente() {
        List<Customer> listaMock = List.of(new Customer());
        Mockito.when(service.getAllCustomers()).thenReturn(listaMock);

        List<Customer> resultado = service.getAllCustomers();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void deveRetornarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(clienteMock));

        Customer resultado = service.getCustomerById(1);
        assertNotNull(resultado);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void naoDeveRetornarUmCliente() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex =assertThrows(ResponseStatusException.class, () -> service.getCustomerById(1));
        assertTrue(ex.getMessage().contains("Não encontrado o cliente com id 1"));
    }

    @Test
    public void deveAdicionarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);
        Mockito.when(repository.save(clienteMock)).thenReturn(clienteMock);

        ShoppingCart carrinhoMock = new ShoppingCart();
        carrinhoMock.setCustomer(clienteMock);
        Mockito.when(carrinhoService.createShoppingCart(clienteMock)).thenReturn(carrinhoMock);

        Customer resultado = service.addCustomer(clienteMock);
        assertNotNull(resultado);
    }

    @Test
    public void deveAtualizarUmCliente() {
        Customer clienteOriginal = new Customer();
        clienteOriginal.setIdCliente(1);
        clienteOriginal.setNomeCliente("Neymar");

        Customer clienteAtualizado = new Customer();
        clienteAtualizado.setNomeCliente("Messi");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(clienteOriginal));
        Mockito.when(repository.save(clienteAtualizado)).thenReturn(clienteAtualizado);

        Customer resultado = service.updateCustomer(1, clienteAtualizado);
        assertNotNull(resultado);
        assertNotEquals("Neymar", resultado.getNomeCliente());
        assertEquals("Messi", resultado.getNomeCliente());
    }

    @Test
    public void deveDeletarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(clienteMock));
        Mockito.doNothing().when(repository).delete(clienteMock);

        service.deleteCustomer(1);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

}