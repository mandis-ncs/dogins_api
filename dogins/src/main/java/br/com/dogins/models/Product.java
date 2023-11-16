package br.com.dogins.models;

import lombok.*;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

@Document("products")
public class Product {

    @Id
    private ObjectId id;

    private String productCategory;
    private String universalProductCode;
    private String productColor;
    private String productDescription;
    private List<Binary> productImages;
    private String brandName;
    private String productWeight;
    private String productPrice;
    private String productStock;
    private String size;
    private String minStock;
    private int amountSales;
    private String productName;
    private List<String> productColors;

}
