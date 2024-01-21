package com.example.productservicedec2023.controllers;
import com.example.productservicedec2023.exceptions.productNotExistsException;
import com.example.productservicedec2023.models.Product;
import com.example.productservicedec2023.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;
    @Autowired
    public ProductController (@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate){

        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){

        ResponseEntity<List<Product>> responseGetAll = new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.OK);
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
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {

        ResponseEntity responsePut = new ResponseEntity( productService.updateProduct(id,product) , HttpStatus.NOT_FOUND);
        return responsePut;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);

    }
}
