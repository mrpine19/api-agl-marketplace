package br.com.fiap.marketPlaceAGL.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.marketPlaceAGL.models.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByCustomerIdCliente(long id);

    Page<ShoppingCart> findByProductsIdProduto(long id, Pageable pageable);

}
 