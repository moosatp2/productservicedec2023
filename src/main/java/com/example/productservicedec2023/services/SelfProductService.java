package com.example.productservicedec2023.services;

import com.example.productservicedec2023.exceptions.productNotExistsException;
import com.example.productservicedec2023.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService  implements ProductService{
    @Override
    public Product getSingleProduct(Long id) throws productNotExistsException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
