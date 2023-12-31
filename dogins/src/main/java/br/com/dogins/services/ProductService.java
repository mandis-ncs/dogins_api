package br.com.dogins.services;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Item;
import br.com.dogins.models.Product;
import br.com.dogins.models.ProductToUpdate;
import br.com.dogins.models.ShoppingCart;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductResponseDto findProductById(String id);

    List<ProductResponseDto> findAllProducts();

    Integer getProductQuantity(String id);

    String updateProductByFields(Boolean purchaseIsConfirmed);

}
