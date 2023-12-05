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

@Document("shopping-cart")
public class ShoppingCart {

    @Id
    private String id;
    private List<Item> itemList;
}
