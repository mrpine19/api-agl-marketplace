package br.com.fiap.marketPlaceAGL.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="TB_AGL_CLIENTE")
public class Customer {

    @Id
    @SequenceGenerator(name = "customerSequence", sequenceName = "TB_AGL_CLIENTE_id_cliente_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    @Column(name = "id_cliente")
    private long idCliente;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "estado_cliente")
    private String estadoCliente;

    @Column(name = "telefone_cliente")
    private String telefoneCliente;

    @Column(name = "email_cliente")
    private String emailCliente;

    @Column(name = "cliente_ativo")
    private char clienteAtivo;
}
