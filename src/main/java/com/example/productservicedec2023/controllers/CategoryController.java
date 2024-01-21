package com.example.productservicedec2023.controllers;

import com.example.productservicedec2023.models.Category;
import com.example.productservicedec2023.repositories.CategoryRepository;
import com.example.productservicedec2023.services.SelfCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")

public class CategoryController {

    private final SelfCategoryService selfCategoryService;
   // private final CategoryRepository categoryRepository;

    public CategoryController(SelfCategoryService selfCategoryService, CategoryRepository categoryRepository) {
        this.selfCategoryService = selfCategoryService;
       // this.categoryRepository = categoryRepository;
    }

    @GetMapping("{name}")
    public Category getSingleCategory(@PathVariable ("name") String name){
        return selfCategoryService.getSingleCategory(name);
    }

    @GetMapping()
    public List<Category> getAllCategory(){
        return selfCategoryService.getAllCategory();
    }
}
