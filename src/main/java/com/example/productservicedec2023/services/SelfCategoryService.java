package com.example.productservicedec2023.services;

import com.example.productservicedec2023.models.Category;
import com.example.productservicedec2023.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository;

    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category getSingleCategory(String name) {
        return categoryRepository.findCategoriesByName(name);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
