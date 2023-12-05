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

@Document("item")
public class Item {

    @Id
    private String id;
    private String productCategory;
    private String productColor;
    private String productDescription;
    private List<String> productImages;
    private List<String> brandName;
    private Double productPrice; //have to convert to string
    private Integer productStock; //have to convert to string
    private String size;
    private String productName;

}
