package br.com.dogins.services.Impl;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.exceptions.ResourceNotFoundException;
import br.com.dogins.models.Product;
import br.com.dogins.repositories.ProductRepository;
import br.com.dogins.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.get().getId());
        productResponseDto.setProductColor(product.get().getProductColor());
        productResponseDto.setProductDescription(product.get().getProductDescription());
        productResponseDto.setProductImages(product.get().getProductImages());
        productResponseDto.setBrandName(product.get().getBrandName());
        productResponseDto.setSize(product.get().getSize());
        productResponseDto.setProductName(product.get().getProductName());

        //convert string price to double
        String productPriceString = product.get().getProductPrice().replace(",", ".");
        Double productPrice = Double.parseDouble(productPriceString);
        productResponseDto.setProductPrice(productPrice);

        productResponseDto.setProductStock(Integer.parseInt(product.get().getProductStock()));


        return productResponseDto;
    }

    @Override
    public ProductResponseDto findCardInfoById(String id) {
        return null;
    }

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Integer getProductQuantity(String id) {
        return null;
    }

    @Override
    public Integer patchProductQuantity(String id, Integer qtdToDelete) {
        return null;
    }
}
