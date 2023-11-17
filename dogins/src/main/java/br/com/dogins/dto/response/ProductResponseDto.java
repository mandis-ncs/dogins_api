package br.com.dogins.dto.response;

import lombok.*;
import org.bson.types.Binary;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductResponseDto {
    String id;
    String productColor;
    String productDescription;
    List<Binary> productImages;
    String brandName;
    String productPrice;
    String productStock;
    String size;
    String productName;
}
