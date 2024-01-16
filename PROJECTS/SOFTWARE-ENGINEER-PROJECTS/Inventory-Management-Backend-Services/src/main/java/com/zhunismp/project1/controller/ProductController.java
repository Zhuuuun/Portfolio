package com.zhunismp.project1.controller;

import com.zhunismp.project1.entity.Product;
import com.zhunismp.project1.exception.ProductNotFoundException;
import com.zhunismp.project1.response.ResponseHandler;
import com.zhunismp.project1.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                productService.findAll()
        );
    }

    @GetMapping("product/needs")
    public ResponseEntity<Object> getNeedProductAmountAll() {
        Map<String,Integer> responseMap = productService.determineNeedAmountAll();

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                responseMap
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable int productId) {
        if(!productService.existsById(productId)) throw new ProductNotFoundException("Product doesn't exists with id : " + productId);
        Product buff = productService.findById(productId);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                buff
        );
    }

    @GetMapping("product/need/{productId}")
    public ResponseEntity<Object> getNeedProductAmount(@PathVariable int productId) {
        if(!productService.existsById(productId)) throw new ProductNotFoundException("Product doesn't exists with id : " + productId);
        Map<String,Integer> responseMap = productService.determineNeedAmount(productId);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                responseMap
        );
    }

    @PostMapping("/product/add")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product buff = productService.save(product);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.CREATED,
                buff
        );
    }

    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable int productId) {
        if(!productService.existsById(productId)) throw new ProductNotFoundException("Product doesn't exists with id : " + productId);
        productService.deleteById(productId);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                null
        );
    }

    @PutMapping("/product/update")
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid Product product) {
        if(!productService.existsById(product.getId())) throw new ProductNotFoundException("Product doesn't exists with id : " + product.getId());
        Product buff = productService.save(product);

        return ResponseHandler.responseBuilder(
                "Request successfully : from " + new Object(){}.getClass().getEnclosingMethod().getName() + "()",
                HttpStatus.OK,
                buff
        );
    }

}
