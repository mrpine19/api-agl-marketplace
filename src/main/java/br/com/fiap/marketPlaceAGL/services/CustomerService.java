package br.com.fiap.marketPlaceAGL.services;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(long id) {
        return customerRepository.findById(id);
    }

    public Customer addCustomer(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(long id, Customer newCustomer) {
        checksIfCustomerExists(id);

        newCustomer.setIdCliente(id);
        return addCustomer(newCustomer);
    }

    public void deleteCustomer(long id) {
        checksIfCustomerExists(id);
        customerRepository.deleteById(id);
    }

    private void checksIfCustomerExists(long id) {
        Optional<Customer> customer = getCustomer(id);

        if (customer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado o cliente com id " + id + " para ser atualizada");
    }
}
