package com.example.productservicedec2023.services;

import com.example.productservicedec2023.exceptions.productNotExistsException;
import com.example.productservicedec2023.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getSingleProduct(Long id) throws productNotExistsException;

    List <Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product addProduct(Product product);

    void deleteProduct(Long id);
}
