package br.com.dogins.controllers;

import br.com.dogins.dto.response.ProductResponseDto;
import br.com.dogins.models.Product;
import br.com.dogins.services.ProductService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dogins")
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

//    @PatchMapping("/{id}")
//    public ResponseEntity<String> patchProductQuantity(@PathVariable String id, String qtdToDelete) {
//        String newQuantity = service.patchProductQuantity(id, qtdToDelete);
//        return ResponseEntity.ok(newQuantity);
//    }

    @PatchMapping("/{id}")
    public Product updateProductFields(@PathVariable String id,@RequestBody Map<String, Object> fields){
        return service.updateProductByFields(id,fields);
    }

    // receber uma list com ids e qtde  para fazer o patch
    // for each -> fzr o patch

}
