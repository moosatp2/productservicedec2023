package com.example.productservicedec2023.services;

import com.example.productservicedec2023.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);

    List <Product> getAllProducts();

    Product replaceProduct(Long id, Product product);
}
