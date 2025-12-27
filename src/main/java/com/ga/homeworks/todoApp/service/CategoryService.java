package com.ga.homeworks.todoApp.service;

import com.ga.homeworks.todoApp.exception.RecordExistsException;
import com.ga.homeworks.todoApp.exception.RecordNotFoundException;
import com.ga.homeworks.todoApp.model.Category;
import com.ga.homeworks.todoApp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {

        Category categoryExists = categoryRepository.findByName(category.getName());
        if (categoryExists != null) {
            throw new RecordExistsException("Category with name " + category.getName() + " already exists");
        }

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category with id " + id + " not found"));
    }

    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category with id " + id + " not found"));

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    }

    public Category deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category with id " + id + " not found"));

        categoryRepository.delete(existingCategory);
        return existingCategory;
    }
}
