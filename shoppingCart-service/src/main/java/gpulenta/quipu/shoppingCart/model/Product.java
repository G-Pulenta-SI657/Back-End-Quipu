package gpulenta.quipu.shoppingCart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImageUrl;
    private int productRating;
    private int productStock;
}
