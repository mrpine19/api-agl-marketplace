package br.com.fiap.marketPlaceAGL.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="TB_AGL_CARRINHO")
@Data
public class ShoppingCart {

    @Id
    @SequenceGenerator(name = "shoppingCartSequence", sequenceName = "TB_AGL_CLIENTE_id_cliente_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoppingCartSequence")
    @Column(name = "id_carrinho")
    private long idCarrinho;

    @OneToOne
    private Customer customers;

}
