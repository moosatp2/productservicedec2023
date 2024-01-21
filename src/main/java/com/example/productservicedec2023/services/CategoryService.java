package com.example.productservicedec2023.services;

import com.example.productservicedec2023.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category getSingleCategory(String name);

    List<Category> getAllCategory();
}
