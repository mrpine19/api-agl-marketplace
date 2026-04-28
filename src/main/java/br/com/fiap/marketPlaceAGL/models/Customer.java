package br.com.fiap.marketPlaceAGL.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_AGL_CLIENTE")
public class Customer {

    @Id
    @SequenceGenerator(name = "customerSequence", sequenceName = "TB_AGL_CLIENTE_id_cliente_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    private long idCliente;

    private String nomeCliente;

    private String estadoCliente;

    private String telefoneCliente;

    private String emailCliente;

    private boolean clienteAtivo;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private ShoppingCart shoppingCart;
}