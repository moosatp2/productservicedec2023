package com.example.productservicedec2023.services;

import com.example.productservicedec2023.dtos.FakeStoreProductDto;
import com.example.productservicedec2023.exceptions.productNotExistsException;
import com.example.productservicedec2023.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws productNotExistsException;

    List <Product> getAllProducts();

    Product replaceProduct(Long id, Product product);

    Product addProduct(Product product);

    Product deleteProduct(Long id);
}
