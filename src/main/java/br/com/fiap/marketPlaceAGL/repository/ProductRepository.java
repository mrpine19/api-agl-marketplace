package br.com.fiap.marketPlaceAGL.repository;

import br.com.fiap.marketPlaceAGL.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
