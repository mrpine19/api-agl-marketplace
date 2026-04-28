package br.com.fiap.marketPlaceAGL.dto;

import br.com.fiap.marketPlaceAGL.models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(

         @NotBlank
         @Size(min = 3, max = 255)
         String nomeProduto,

         @NotNull
         float precoProduto,

         @NotNull
         int quantidadeEstoque,

         @NotNull
         boolean produtoDisponivel

) {
    public Product toEntity(){
        return Product.builder()
                .nomeProduto(nomeProduto)
                .precoProduto(precoProduto)
                .quantidadeEstoque(quantidadeEstoque)
                .produtoDisponivel(produtoDisponivel)
                .build();
    }
}
