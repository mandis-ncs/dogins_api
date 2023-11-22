package br.com.dogins.services.Impl;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.exceptions.InvalidValue;
import br.com.dogins.exceptions.ListIsEmptyException;
import br.com.dogins.exceptions.ResourceNotFoundException;
import br.com.dogins.models.Product;
import br.com.dogins.models.ProductToUpdate;
import br.com.dogins.repositories.ProductRepository;
import br.com.dogins.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductResponseDto findProductById(String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("This product id does not exist: " + id);
        }
        return mapToProductResponseDto(product);
    }

    private ProductResponseDto mapToProductResponseDto(Optional<Product> product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.get().getId());
        productResponseDto.setProductColor(product.get().getProductColor());
        productResponseDto.setProductDescription(product.get().getProductDescription());
        productResponseDto.setProductImages(product.get().getProductImages());
        productResponseDto.setBrandName(product.get().getBrandName());
        productResponseDto.setSize(product.get().getSize());
        productResponseDto.setProductName(product.get().getProductName());

        //convert string price to double
        productResponseDto.setProductPrice(convertStrToDouble(product.get().getProductPrice()));
        productResponseDto.setProductStock(Integer.parseInt(product.get().getProductStock()));
        return productResponseDto;
    }

    private Double convertStrToDouble(String str) {
        // Convert string to double, handling commas
        String strWithoutComma = str.replace(",", ".");
        return Double.parseDouble(strWithoutComma);
    }


    @Override
    public List<ProductResponseDto> findAllProducts() {
        var productList = repository.findAll();
        if (productList.isEmpty()) {
            throw new ListIsEmptyException("No products found!");
        }

        List<ProductResponseDto> responseDtoList = new ArrayList<>();

        for (Product product : productList) {
            responseDtoList.add(mapToProductResponseDto(Optional.ofNullable(product)));
        }

        return responseDtoList;
    }

    @Override
    public Integer getProductQuantity(String id) {
        ProductResponseDto responseDto = findProductById(id);
        return Integer.parseInt(String.valueOf(responseDto.getProductStock()));
    }

    @Override
    public String patchProductQuantity(String id, String qtdToDelete) {
        return null;
    }


    @Override
    public void updateProductByFields(List<ProductToUpdate> productToUpdateList) {
        log.info("### going to run the list ###");

        for (ProductToUpdate productToUpdate : productToUpdateList) {
            // Find the product in the database
            Optional<Product> productOptional = repository.findById(productToUpdate.getId());
            log.info("### called find by id ###");

            if (productOptional.isPresent()) {
                // Get the product
                Product product = productOptional.get();
                log.info("### found product ###");

                // Update the productStock field
                product.setProductStock(productToUpdate.getProductStock());
                log.info("### updated product ###");

                // Save the updated product back to the database
                repository.save(product);
                log.info("### save the product ###");
            } else {
                log.warn("Product with id " + productToUpdate.getId() + " not found");
            }
        }
    }



}
