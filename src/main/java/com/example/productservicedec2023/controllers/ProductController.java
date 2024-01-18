package com.example.productservicedec2023.controllers;

import com.example.productservicedec2023.dtos.FakeStoreProductDto;
import com.example.productservicedec2023.exceptions.productNotExistsException;
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

        ResponseEntity<List<Product>> responseGetAll = new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.ALREADY_REPORTED);
        return responseGetAll;

    }

    @GetMapping("/{id}" )
    public ResponseEntity<Product> getSingleProduct(@PathVariable ("id") Long id ) throws productNotExistsException {

        ResponseEntity<Product> responseGet = new
                ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return responseGet;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        ResponseEntity responsePost =
                new ResponseEntity(productService.addProduct(product), HttpStatus.OK);
        return responsePost;
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
