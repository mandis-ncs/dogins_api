package br.com.dogins.services;

import br.com.dogins.models.Product;
import org.bson.types.ObjectId;

public interface ProductService {
    Product findProductById(ObjectId id);
}
