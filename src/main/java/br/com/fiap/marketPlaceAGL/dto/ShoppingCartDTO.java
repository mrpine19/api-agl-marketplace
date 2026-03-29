package br.com.fiap.marketPlaceAGL.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppingCartDTO {
    private long idCustomer;
    private long idProduct;
}
