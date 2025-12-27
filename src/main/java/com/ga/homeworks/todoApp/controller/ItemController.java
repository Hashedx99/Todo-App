package com.ga.homeworks.todoApp.controller;

import com.ga.homeworks.todoApp.model.Item;
import com.ga.homeworks.todoApp.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories/{categoryId}/items")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;


    @GetMapping
    public List<Item> getAllItems(@PathVariable("categoryId") long categoryId) {
        return itemService.getAllItems(categoryId);
    }

    @PostMapping
    public Item createItem(@PathVariable("categoryId") long categoryId, @RequestBody Item item) {
        return itemService.createItem(categoryId, item);
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable("categoryId") long categoryId, @PathVariable("itemId") long itemId) {
        return itemService.getItem(categoryId, itemId);
    }

    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable("categoryId") long categoryId, @PathVariable("itemId") long itemId,
                           @RequestBody Item item) {
        return itemService.updateItem(categoryId, itemId, item);
    }

    @DeleteMapping("/{itemId}")
    public Item deleteItem(@PathVariable("categoryId") long categoryId, @PathVariable("itemId") long itemId) {
        return itemService.deleteItem(categoryId, itemId);
    }
}
