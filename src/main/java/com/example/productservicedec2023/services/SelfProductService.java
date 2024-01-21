package com.example.productservicedec2023.services;

import com.example.productservicedec2023.exceptions.productNotExistsException;
import com.example.productservicedec2023.models.Category;
import com.example.productservicedec2023.models.Product;
import com.example.productservicedec2023.repositories.CategoryRepository;
import com.example.productservicedec2023.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService  implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws productNotExistsException {

        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()){
            throw new productNotExistsException("no product found with id : " +id);
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    @Override
    public Product updateProduct(Long id, Product product) {

        Optional<Product> productOptional = productRepository.findById(id);
       // Optional<Category> categoryOptional = categoryRepository.findByName();
        Product productSaved = productOptional.get();

        if(product.getTitle() != null){
            productSaved.setTitle(product.getTitle());
        }

        if(product.getDescription() != null){
            productSaved.setDescription(product.getDescription());
        }

        if(product.getPrice() != null){
            productSaved.setPrice(product.getPrice());
        }

        if(product.getImageUrl() !=null){
            productSaved.setImageUrl(product.getImageUrl());
        }
        return productRepository.save(productSaved);
    }

    @Override
    public Product addProduct(Product product) {

        Optional<Category> categoryOptional =
                categoryRepository.findByName(product.getCategory().getName());

        if (categoryOptional.isEmpty()){
            //product.setCategory(categoryRepository.save(product.getCategory()));

        } else {
            product.setCategory(categoryOptional.get());
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
        System.out.println("product with id: " + id + " removed");
    }
}
