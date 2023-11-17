package br.com.dogins.services;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Product;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductResponseDto findProductById(String id);

    List<ProductResponseDto> findAllProducts();

    Integer getProductQuantity(String id);

    String patchProductQuantity(String id, String qtdToDelete);

    Product updateProductByFields(String id, Map<String, Object> fields);

}
