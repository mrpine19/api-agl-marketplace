package br.com.fiap.marketPlaceAGL.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_AGL_PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", insertable = false)
    private Long idProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "preco_produto")
    private float precoProduto;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    @Column(name = "produto_disponivel")
    private char produtoDisponivel;

}
