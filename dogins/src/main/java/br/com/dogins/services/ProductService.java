package br.com.dogins.services;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Product;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {
    ProductResponseDto findProductById(String id);

    List<ProductResponseDto> findAllProducts();

    Integer getProductQuantity(String id);

    Integer patchProductQuantity(String id, Integer qtdToDelete);

}
