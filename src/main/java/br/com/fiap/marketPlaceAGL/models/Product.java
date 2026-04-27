package br.com.fiap.marketPlaceAGL.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_AGL_PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Long idProduto;

    private String nomeProduto;

    private float precoProduto;

    private int quantidadeEstoque;

    private boolean produtoDisponivel;

}
