package br.com.dogins.dto.response;

import lombok.*;
import org.bson.types.Binary;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductCardResponseDto {
    String id;
    List<Binary> productImages;
    String productPrice;
    String productStock;
    String productName;
}
