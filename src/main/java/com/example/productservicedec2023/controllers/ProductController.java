package com.example.productservicedec2023.controllers;

import com.example.productservicedec2023.models.Product;
import com.example.productservicedec2023.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.ALREADY_REPORTED);
        return response;

    }

    @GetMapping("/{id}" )
    public Product getSingleProduct(@PathVariable ("id") Long id ){
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        Product p = new Product();
        p.setTitle("a new product");
        return p;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new Product();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {

        ResponseEntity responsePut = new ResponseEntity( productService.replaceProduct(id,product) , HttpStatus.NOT_FOUND);
        return responsePut;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
    }
}
