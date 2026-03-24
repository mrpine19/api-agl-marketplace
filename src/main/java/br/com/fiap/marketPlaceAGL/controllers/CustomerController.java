package br.com.fiap.marketPlaceAGL.controllers;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers(){
        log.info("Buscando todos os clientes");
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") long id){
        log.info("Buscando cliente com id " + id);
        return customerService.getCustomer(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
}
