package br.com.dogins.services.Impl;

import br.com.dogins.models.Product;
import br.com.dogins.repositories.ProductRepository;
import br.com.dogins.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findProductById(ObjectId id) {
        Optional<Product> response = repository.findById(id);
        return response.orElseThrow(() -> new RuntimeException("That product dont exists"));
    }
}
