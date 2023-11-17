package br.com.dogins.services;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Product;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {
    ProductResponseDto findProductById(String id);

    ProductResponseDto findCardInfoById(String id); //Product Info to Shopping Cart

    List<Product> findAllProducts();

    Integer getProductQuantity(String id);

    Integer patchProductQuantity(String id, Integer qtdToDelete);

}
