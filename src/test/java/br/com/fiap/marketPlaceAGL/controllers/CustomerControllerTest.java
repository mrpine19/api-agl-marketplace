package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    @Test
    public void deveRetornarTodosOsClientesPaginados() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Customer> paginaMock = new PageImpl<>(List.of(new Customer()));
        Mockito.when(service.getAllCustomers(isNull(), isNull(), eq(pageable))).thenReturn(paginaMock);

        Page<Customer> resultado = controller.getAllCustomers(null, null, pageable);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.getContent().size());
        Mockito.verify(service, Mockito.times(1)).getAllCustomers(isNull(), isNull(), eq(pageable));
    }

    @Test
    public void deveRetornarApenasUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);
        Mockito.when(service.getCustomerById(1)).thenReturn(clienteMock);

        ResponseEntity<Customer> resultado = controller.getCustomer(1);
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals(1, resultado.getBody().getIdCliente());
    }

    @Test
    public void deveAdicionarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);
        Mockito.when(service.addCustomer(clienteMock)).thenReturn(clienteMock);

        ResponseEntity<Customer> resultado = controller.addCliente(clienteMock);
        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertEquals(1, resultado.getBody().getIdCliente());
    }

    @Test
    public void deveAtualizarUmCliente() {
        Customer clienteOriginal = new Customer();
        clienteOriginal.setIdCliente(1);
        clienteOriginal.setNomeCliente("Neymar");

        Customer clienteAtualizado = new Customer();
        clienteAtualizado.setNomeCliente("Messi");

        Mockito.when(service.updateCustomer(1, clienteAtualizado)).thenReturn(clienteAtualizado);

        ResponseEntity<Customer> resultado = controller.updateCustomer(1, clienteAtualizado);
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());

        Customer body = resultado.getBody();
        assertNotNull(body);
        assertNotEquals("Neymar", body.getNomeCliente());
        assertEquals("Messi", body.getNomeCliente());
    }

    @Test
    public void deveDeletarUmCliente() {
        Customer clienteMock = new Customer();
        clienteMock.setIdCliente(1);

        ResponseEntity<Void> resultado = controller.deleteCustomer(1);
        assertNotNull(resultado);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).deleteCustomer(1);
    }

}
