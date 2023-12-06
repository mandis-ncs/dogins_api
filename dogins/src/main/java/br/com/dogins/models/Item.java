package br.com.dogins.models;

import jakarta.validation.constraints.NotNull;
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
    private List<String> image;
    private String title;
    private Double discount;
    private Integer inStock;
    private Integer quantity;
    private Double price;
    private Double total;

}
