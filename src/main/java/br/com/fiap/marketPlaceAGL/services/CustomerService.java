package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.projections.CustomerProjection;
import br.com.fiap.marketPlaceAGL.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

    public Page<Customer> getAllCustomers(String estadoCliente, Boolean clienteAtivo, Pageable pageable) {
        if (estadoCliente != null && !estadoCliente.isBlank() && clienteAtivo != null)
            return customerRepository.findByEstadoClienteAndClienteAtivo(estadoCliente, clienteAtivo, pageable);

        if (estadoCliente != null && !estadoCliente.isBlank())
            return customerRepository.findByEstadoCliente(estadoCliente, pageable);

        if (clienteAtivo != null)
            return customerRepository.findByClienteAtivo(clienteAtivo, pageable);

        return customerRepository.findAll(pageable);
    }

    public Page<CustomerProjection> getCustomersByName(String nomeCliente, Pageable pageable) {
        return customerRepository.findByNomeClienteContainingIgnoreCase(nomeCliente, pageable);
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado o cliente com id " + id));
    }

    public Customer addCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(newCustomer);
        return shoppingCart.getCustomer();
    }

    public Customer updateCustomer(long id, Customer newCustomer) {
        getCustomerById(id);
        newCustomer.setIdCliente(id);
        return customerRepository.save(newCustomer);
    }

    public void deleteCustomer(long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}
