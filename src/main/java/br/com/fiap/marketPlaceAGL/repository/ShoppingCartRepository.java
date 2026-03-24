package br.com.fiap.marketPlaceAGL.repository;

import br.com.fiap.marketPlaceAGL.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
