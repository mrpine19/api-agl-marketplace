package br.com.fiap.marketPlaceAGL.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Entity
@Table(name="TB_AGL_CARRINHO")
@Getter
@Setter

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

    public void addItemShoppingCart(Product product){
        products.add(product);
    }

    public void removeItemShoppingCart(long idProduct){
        boolean removed = products.removeIf(product -> product.getIdProduto().equals(idProduct));

        if(!removed)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este produto não está no carrinho. Seu burro!");
    }
}
