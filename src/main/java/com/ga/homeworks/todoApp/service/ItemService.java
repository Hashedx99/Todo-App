package com.ga.homeworks.todoApp.service;

import com.ga.homeworks.todoApp.exception.RecordNotFoundException;
import com.ga.homeworks.todoApp.model.Category;
import com.ga.homeworks.todoApp.model.Item;
import com.ga.homeworks.todoApp.repository.CategoryRepository;
import com.ga.homeworks.todoApp.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    public List<Item> getAllItems(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RecordNotFoundException(
                "Category " +
                        "not found with id " + categoryId));
        return category.getItemList();
    }

    public Item createItem(Long categoryId, Item item) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Category not found with id " + categoryId));

        item.setCategory(category);

        return itemRepository.save(item);
    }

    public Item getItem(Long categoryId, Long itemId) {
        return itemRepository.findItemByIdAndCategoryId(itemId, categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Item with id " + itemId + " and category id " + categoryId +
                        " not found"));
    }

    public Item updateItem(Long categoryId, Long itemId, @RequestBody Item item) {
        Item existingItem = itemRepository.findItemByIdAndCategoryId(itemId, categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Item with id " + itemId + " and category id" + categoryId + " " +
                        " not found"));

        existingItem.setName(item.getName());
        existingItem.setDescription(item.getDescription());
        existingItem.setDueDate(item.getDueDate());

        return itemRepository.save(existingItem);
    }

    public Item deleteItem(Long categoryId, Long itemId) {
        Item existingItem = itemRepository.findItemByIdAndCategoryId(itemId, categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Item with id " + itemId + " and category id " + categoryId +
                        " not found"));

        itemRepository.delete(existingItem);
        return existingItem;
    }

}
