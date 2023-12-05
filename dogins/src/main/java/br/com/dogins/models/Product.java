package br.com.dogins.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

@Document("products")
public class Product {

    @Id
    private String id;

    private String productCategory;
    private String universalProductCode;
    private String productColor;
    private String productDescription;
    private List<String> productImages;
    private String brandName;
    private String productWeight;
    private String productPrice;
    private String productStock;
    private String size;
    private Integer minStock;
    private Integer amountSales;
    private String productName;
    private List<String> productColors;

}
