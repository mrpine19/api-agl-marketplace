package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController controller;

    @Test
    public void deveRetornarTodosOsClientes() {
        List<Customer> listaMock = List.of(new Customer());
        Mockito.when(controller.getAllCustomers()).thenReturn(listaMock);

        List<Customer> resultado = controller.getAllCustomers();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        Mockito.verify(customerService, Mockito.times(1)).getAllCustomers();
    }

    @Test
    public void deveRetornarApenasUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);
        Mockito.when(customerService.getCustomerById(1)).thenReturn(clienteMock);

        ResponseEntity resultado = controller.getCustomer(1);
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    public void deveAdicionarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);
        Mockito.when(customerService.addCustomer(clienteMock)).thenReturn(clienteMock);

        ResponseEntity resultado = controller.addCliente(clienteMock);
        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }

    @Test
    public void deveAtualizarUmCliente() {
        Customer clienteOriginal = new Customer();
        clienteOriginal.setIdCliente(1);
        clienteOriginal.setNomeCliente("Neymar");

        Customer clienteAtualizado = new Customer();
        clienteAtualizado.setNomeCliente("Messi");

        Mockito.when(customerService.updateCustomer(1, clienteAtualizado)).thenReturn(clienteAtualizado);

        ResponseEntity resultado = controller.updateCustomer(1, clienteAtualizado);
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        Customer body = (Customer) resultado.getBody();
        assertNotEquals("Neymar", body.getNomeCliente());
        assertEquals("Messi", body.getNomeCliente());
    }

    @Test
    public void deveDeletarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);

        ResponseEntity resultado = controller.deleteCustomer(1);
        assertNotNull(resultado);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }

}