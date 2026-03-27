package br.com.fiap.marketPlaceAGL.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="TB_AGL_CARRINHO")
@Getter
@Setter

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idCarrinho")
public class ShoppingCart {

    @Id
    @SequenceGenerator(name = "shoppingCartSequence", sequenceName = "TB_AGL_CARRINHO_ID_CARRINHO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoppingCartSequence")
    @Column(name = "id_carrinho")
    private long idCarrinho;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    @JsonIdentityReference(alwaysAsId = true)
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "TB_AGL_PRODUTOS_CARRINHO",
            joinColumns = @JoinColumn(name = "id_carrinho"),
            inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private List<Product> products;

    public ShoppingCart() {
    }

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public ShoppingCart(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }
}
