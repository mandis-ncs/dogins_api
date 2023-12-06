package br.com.dogins.services.Impl;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.exceptions.ListIsEmptyException;
import br.com.dogins.exceptions.PurchaseFailedException;
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

    public ProductServiceImpl(ProductRepository repository, ShoppingCartRepository shoppingCartRepository) {
        this.repository = repository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ProductResponseDto findProductById(String id) {
        log.info("findProductById called");
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("This product id does not exist: " + id);
        }
        return mapToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        log.info("findAllProducts called");
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
        log.info("getProductQuantity called");
        ProductResponseDto responseDto = findProductById(id);
        return Integer.parseInt(String.valueOf(responseDto.getProductStock()));
    }


    @Override
    public String updateProductByFields(Boolean purchaseIsConfirmed) {
        log.info("updateProductByFields called");
        if (purchaseIsConfirmed == false) {
            throw new PurchaseFailedException("Your purchase was denied! Please try again later.");
        }

        List<ShoppingCart> savedShoppingCartList = shoppingCartRepository.findAll();

        if(savedShoppingCartList.isEmpty()) {
            throw new ListIsEmptyException("Shopping cart not found.");
        }

        //update product collection in database with the information of the items in shopping cart
        updateDatabaseWithShoppingCart(savedShoppingCartList);

        //delete shopping cart
        shoppingCartRepository.deleteAll();
        return "Your purchase was confirmed!";
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

    private Product mapToProduct(ProductResponseDto productResponseDto) {
        Product product = new Product();
        product.setId(productResponseDto.getId());
        product.setProductColor(productResponseDto.getProductColor());
        product.setProductDescription(productResponseDto.getProductDescription());
        product.setProductImages(productResponseDto.getProductImages());
        product.setBrandName(productResponseDto.getBrandName());
        product.setSize(productResponseDto.getSize());
        product.setProductName(productResponseDto.getProductName());

        //convert double price to string
        product.setProductPrice(Double.toString(productResponseDto.getProductPrice()));
        product.setProductStock(Integer.toString(productResponseDto.getProductStock()));
        return product;
    }


    private Double convertStrToDouble(String str) {
        // Convert string to double, handling commas
        String strWithoutComma = str.replace(",", ".");
        return Double.parseDouble(strWithoutComma);
    }

    private void updateDatabaseWithShoppingCart (List<ShoppingCart> savedShoppingCartList) {
        for (ShoppingCart savedShoppingCart : savedShoppingCartList) {
            List<Item> listOfItensInShoppingCart = savedShoppingCart.getItemList();

            for (Item item : listOfItensInShoppingCart) {
                Integer newQuantityInStock = item.getInStock() - item.getQuantity();

                //get product with item id
                String id = item.getId().toString();
                ProductResponseDto productResponseDto = findProductById(id);
                if (productResponseDto == null) {
                    throw new ResourceNotFoundException("Product not found with id: " + item.getId());
                }

                Product product = mapToProduct(productResponseDto);
                product.setProductStock(newQuantityInStock.toString());
                repository.save(product);
            }
        }
    }


}
