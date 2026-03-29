package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import br.com.fiap.marketPlaceAGL.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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
        getCustomerById(id);
        customerRepository.deleteById(id);
    }
}
