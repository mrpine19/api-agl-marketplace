package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.projections.CustomerProjection;
import br.com.fiap.marketPlaceAGL.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomerService customerService;

    @GetMapping
    public Page<Customer> getAllCustomers(
            @RequestParam(required = false) String estadoCliente,
            @RequestParam(required = false) Boolean clienteAtivo,
            Pageable pageable) {
        log.info("Buscando clientes com paginação, ordenação e filtros: estadoCliente={}, clienteAtivo={}", estadoCliente, clienteAtivo);
        return customerService.getAllCustomers(estadoCliente, clienteAtivo, pageable);
    }

    @GetMapping("/search")
    public Page<CustomerProjection> getCustomersByName(@RequestParam String nomeCliente, Pageable pageable){
        log.info("Buscando clientes por nome com projection resumida");
        return customerService.getCustomersByName(nomeCliente, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id){
        log.info("Buscando cliente com id " + id);
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> addCliente (@RequestBody Customer newCustomer){
        log.info("Adicionando um novo cliente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.addCustomer(newCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer){
        log.info("Atualizando o cliente com id " + id);
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") long id){
        log.info("Deletando o cliente com id " + id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
