package br.com.fiap.marketPlaceAGL.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="TB_AGL_CARRINHO")
@Data
public class ShoppingCart {

    @Id
    @Column(name = "id_carrinho")
    private long idCarrinho;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "TB_AGL_PRODUTOS_CARRINHO",
            joinColumns = @JoinColumn(name = "id_carrinho"),
            inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private List<Product> products;

}
