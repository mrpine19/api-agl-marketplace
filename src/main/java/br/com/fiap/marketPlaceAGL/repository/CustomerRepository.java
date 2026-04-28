package br.com.fiap.marketPlaceAGL.repository;

import br.com.fiap.marketPlaceAGL.models.Customer;
import br.com.fiap.marketPlaceAGL.projections.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByEstadoClienteAndClienteAtivo(String estadoCliente, Boolean clienteAtivo, Pageable pageable);

    Page<Customer> findByEstadoCliente(String estadoCliente, Pageable pageable);

    Page<Customer> findByClienteAtivo(Boolean clienteAtivo, Pageable pageable);

    Page<CustomerProjection> findByNomeClienteContainingIgnoreCase(String nomeCliente, Pageable pageable);
}
