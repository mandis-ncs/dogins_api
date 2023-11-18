package br.com.dogins.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductToUpdate {

    @Id
    private String id;
    private String productStock;
}
