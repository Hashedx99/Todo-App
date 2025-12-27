package com.ga.homeworks.todoApp.controller;

import com.ga.homeworks.todoApp.model.Category;
import com.ga.homeworks.todoApp.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        return  categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        log.info("Creating new category: {}", category);
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        log.info("Fetching category with id: {}", id);
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long id, @RequestBody Category category) {
        log.info("Updating category: {}", category);
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public Category deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with id: {}", id);
        return categoryService.deleteCategory(id);
    }
}
