package br.com.dogins.controllers;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Item;
import br.com.dogins.models.Product;
import br.com.dogins.models.ProductToUpdate;
import br.com.dogins.services.ProductService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dogins/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable String id) {
        var response = service.findProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        var response = service.findAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/quantity")
    public ResponseEntity<Integer> getProductQuantity(@PathVariable String id) {
        var response = service.getProductQuantity(id);
        return ResponseEntity.ok(response);
    }


//    @PatchMapping                                                   //List de Update e fields
//    public ResponseEntity<Void> updateProductFields(@RequestBody List<ProductToUpdate> productToUpdateList){
//        service.updateProductByFields(productToUpdateList);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/shopping-cart")
    public ResponseEntity<List<Item>> createShoppingCart(@RequestBody List<Item> shoppingCartItensList){
        service.postInShoppingCart(shoppingCartItensList);
        return ResponseEntity.ok().build();
    }

    // receber uma list com itens, guardar no carrinho
    // esperar confirm para deletar dados do carrinho -> update da qtde (na api) - reload quantity

}
