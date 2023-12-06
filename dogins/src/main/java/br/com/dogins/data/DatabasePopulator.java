package br.com.dogins.data;
import br.com.dogins.models.Product;
import br.com.dogins.repositories.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;

@Component
public class DatabasePopulator implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;

    public DatabasePopulator(MongoTemplate mongoTemplate, ProductRepository productRepository) {
        this.mongoTemplate = mongoTemplate;
        this.productRepository = productRepository;
    }

    private List<Product> loadProductsFromJson() {
        try {
            File file = new ClassPathResource("products.json").getFile();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar produtos do arquivo JSON", e);
        }
    }

    @Override
    public void run(String... args) {
        mongoTemplate.getDb().drop();
        mongoTemplate.getDb().createCollection("products");

        if (productRepository.count() == 0) {
            List<Product> products = loadProductsFromJson();
            productRepository.insert(products);
            System.out.println("Database populated with products.");
        }
    }

}
