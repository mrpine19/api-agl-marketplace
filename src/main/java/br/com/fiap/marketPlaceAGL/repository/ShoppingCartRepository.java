package br.com.fiap.marketPlaceAGL.repository;

import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.customer.idCliente = ?1")
    Optional<ShoppingCart> getShoppingCartByCustomerId (long id);

}
