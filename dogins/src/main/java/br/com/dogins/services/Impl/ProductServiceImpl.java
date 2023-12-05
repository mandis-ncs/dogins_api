package br.com.dogins.services.Impl;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.exceptions.ListIsEmptyException;
import br.com.dogins.exceptions.ResourceNotFoundException;
import br.com.dogins.models.Item;
import br.com.dogins.models.Product;
import br.com.dogins.models.ProductToUpdate;
import br.com.dogins.models.ShoppingCart;
import br.com.dogins.repositories.ProductRepository;
import br.com.dogins.repositories.ShoppingCartRepository;
import br.com.dogins.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final ShoppingCartRepository shoppingCartRepository;

    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository repository, ModelMapper modelMapper, ShoppingCartRepository shoppingCartRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.shoppingCartRepository = shoppingCartRepository;
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
    public void updateProductByFields(List<ProductToUpdate> productToUpdateList) {

        for (ProductToUpdate productToUpdate : productToUpdateList) {
            Optional<Product> productOptional = repository.findById(productToUpdate.getId());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                product.setProductStock(productToUpdate.getQuantityPicked());
                repository.save(product);
            } else {
                log.warn("Product with id " + productToUpdate.getId() + " not found");
                throw new ResourceNotFoundException("Produto não encontrado com id = " + productToUpdate.getId());
            }
        }
    }

    @Override
    public List<Item> postInShoppingCart(List<Item> shoppingCartItemsList) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setItemList(shoppingCartItemsList);
        shoppingCartRepository.save(shoppingCart);

        ShoppingCart savedShoppingCart = shoppingCartRepository.findById(shoppingCart.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ShoppingCart was empty or did not exist"));

        return savedShoppingCart.getItemList();
    }




}
