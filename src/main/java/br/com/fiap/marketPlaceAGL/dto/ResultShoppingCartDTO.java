package br.com.fiap.marketPlaceAGL.dto;

import br.com.fiap.marketPlaceAGL.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultShoppingCartDTO {
    private long idShoppingCart;
    private long idCustomer;
    private List<Product> products;
}
