package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Category;
import com.twovtwok.backend.rep.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
}
