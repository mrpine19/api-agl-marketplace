package br.com.fiap.marketPlaceAGL.dto;

import br.com.fiap.marketPlaceAGL.models.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest (

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nomeCliente,

        @NotBlank(message = "Estado é obrigatório")
        @Pattern(
                regexp = "^[A-Z]{2}$",
                message = "Estado deve estar no formato UF (ex: SP, RJ)"
        )
        String estadoCliente,

        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido (ex: (11) 91234-5678)"
        )
        String telefoneCliente,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String emailCliente,

        boolean clienteAtivo
) {
    public Customer toEntity(){
        return Customer.builder()
                .nomeCliente(nomeCliente)
                .estadoCliente(estadoCliente)
                .telefoneCliente(telefoneCliente)
                .emailCliente(emailCliente)
                .clienteAtivo(clienteAtivo)
                .build();
    }
}
