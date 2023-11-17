package br.com.dogins.services.Impl;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.exceptions.InvalidValue;
import br.com.dogins.exceptions.ListIsEmptyException;
import br.com.dogins.exceptions.ResourceNotFoundException;
import br.com.dogins.models.Product;
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
    public Product updateProductByFields(String id, Map<String, Object> fields) {
        Optional<Product> existingProduct = repository.findById(id);

        if (existingProduct.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct.get(), value);
            });
            return repository.save(existingProduct.get());
        }
        return null;
    }


}
