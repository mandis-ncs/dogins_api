package br.com.dogins.controllers;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Item;
import br.com.dogins.models.Product;
import br.com.dogins.models.ProductToUpdate;
import br.com.dogins.services.ProductService;
import br.com.dogins.services.ShoppingCartService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dogins")
public class ProductController {

    private ProductService service;

    private ShoppingCartService shoppingCartService;

    public ProductController(ProductService service, ShoppingCartService shoppingCartService) {
        this.service = service;
        this.shoppingCartService = shoppingCartService;
    }

    //############## PRODUCT STOCK ENDPOINTS ##############

    //http://localhost:8080/dogins/products/656d4191ab73ee5765012ead
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable String id) {
        var response = service.findProductById(id);
        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/dogins/products
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        var response = service.findAllProducts();
        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/dogins/products/656d4191ab73ee5765012ead/quantity
    @GetMapping("/products/{id}/quantity")
    public ResponseEntity<Integer> getProductQuantity(@PathVariable String id) {
        var response = service.getProductQuantity(id);
        return ResponseEntity.ok(response);
    }

    //############## SHOPPING CART AND PRODUCT STOCK ENDPOINT ##############
    //http://localhost:8080/dogins/products/true
    //call when pressed and confirmed purchase in shopping cart
    @PatchMapping("/products/{purchaseIsConfirmed}")
    public ResponseEntity<String> shoppingCartConfirmed(@PathVariable Boolean purchaseIsConfirmed){
        String response = service.updateProductByFields(purchaseIsConfirmed); //inside service call drop shopping cart
        return ResponseEntity.ok(response);
    }

    //############## SHOPPING CART ENDPOINTS ##############

    //http://localhost:8080/dogins/shopping-cart
    //call when adding products to shopping cart
    @PostMapping("/shopping-cart")
    public ResponseEntity<List<Item>> createShoppingCart(@RequestBody List<Item> shoppingCartItensList){
        var response = shoppingCartService.postInShoppingCart(shoppingCartItensList);
        return ResponseEntity.ok(response);
    }


    //http://localhost:8080/dogins/shopping-cart/item/656d4191ab73ee5765012ead
    @GetMapping("/shopping-cart/item/{id}") //Item ID
    public ResponseEntity<Item> findItemByIdInShoppingCart(@PathVariable String id) {
        var response = shoppingCartService.findItemByIdInShoppingCart(id);
        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/dogins/shopping-cart
    @GetMapping("/shopping-cart")
    public ResponseEntity<List<Item>> findAllItemsInShoppingCart() {
        var response = shoppingCartService.findAllItemsInShoppingCart();
        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/dogins/shopping-cart/item/656d4191ab73ee5765012ead
    @DeleteMapping("/shopping-cart/item/{id}")
    public ResponseEntity<Void> deleteItemInShoppingCart(@PathVariable String id) {
        shoppingCartService.deleteItemInShoppingCart(id);
        return ResponseEntity.ok().build();
    }
}
