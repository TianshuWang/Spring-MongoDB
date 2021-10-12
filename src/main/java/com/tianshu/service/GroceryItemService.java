package com.tianshu.service;

import com.tianshu.dao.ItemRepository;
import com.tianshu.domain.GroceryItem;
import com.tianshu.dto.GroceryItemDto;
import com.tianshu.exception.GroceryItemNotFoundException;
import com.tianshu.exception.GroceryItemQuantityException;
import com.tianshu.exception.GroceryItemsNotFoundByCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryItemService {

    @Autowired
    private ItemRepository itemRepository;

    public GroceryItemDto createGroceryItem(GroceryItemDto groceryItemDto){
        GroceryItem groceryItem = dtoToGroceryItem(groceryItemDto);

        itemRepository.insert(groceryItem);

        return groceryItemDto;
    }

    public GroceryItemDto updateGroceryItem(GroceryItemDto groceryItemDto) throws GroceryItemNotFoundException {
        GroceryItem groceryItem = getGroceryItem(groceryItemDto.getName());

        groceryItem = dtoToGroceryItem(groceryItemDto);

        itemRepository.save(groceryItem);

        return groceryItemDto;
    }

    public GroceryItemDto findGroceryItemByName(String name) throws GroceryItemNotFoundException {
        GroceryItem groceryItem = getGroceryItem(name);

        return groceryItemToDto(groceryItem);
    }

    public List<GroceryItemDto> findAllByCategory(String category) throws GroceryItemsNotFoundByCategoryException {
        List<GroceryItem> groceryItems = itemRepository.findAllByCategory(category);

        if(groceryItems == null || groceryItems.size() == 0)
            throw new GroceryItemsNotFoundByCategoryException("Grocery Item with category: " + category + " are not found.");

        List<GroceryItemDto> groceryItemDtos = new ArrayList<GroceryItemDto>();

        groceryItems.forEach(g -> groceryItemDtos.add(groceryItemToDto(g)));

        return groceryItemDtos.stream().sorted(Comparator.comparing(GroceryItemDto::getQuantity).reversed()).collect(Collectors.toList());
    }

    public GroceryItemDto consumeGroceryItem(String name, int quantity) throws GroceryItemNotFoundException {
        GroceryItem groceryItem = getGroceryItem(name);

        int stock = groceryItem.getQuantity();

        if(groceryItem.getQuantity() < quantity)
            throw new GroceryItemQuantityException("There's no stock sufficient for the Grocery Item: " + name);

        groceryItem.setQuantity(stock - quantity);

        itemRepository.save(groceryItem);

        return groceryItemToDto(groceryItem);
    }

    private GroceryItemDto groceryItemToDto(GroceryItem groceryItem){
        GroceryItemDto dto = new GroceryItemDto();
        dto.setId(groceryItem.getId());
        dto.setName(groceryItem.getName());
        dto.setQuantity(groceryItem.getQuantity());
        dto.setCategory(groceryItem.getCategory());

        return dto;
    }

    private GroceryItem dtoToGroceryItem(GroceryItemDto groceryItemDto){
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setId(groceryItemDto.getId());
        groceryItem.setName(groceryItemDto.getName());
        groceryItem.setQuantity(groceryItemDto.getQuantity());
        groceryItem.setCategory(groceryItemDto.getCategory());

        return groceryItem;
    }

    private GroceryItem getGroceryItem(String name) throws GroceryItemNotFoundException {
        GroceryItem groceryItem = itemRepository.findItemByName(name);

        if(groceryItem == null)
            throw new GroceryItemNotFoundException("Grocery Item with name: " + name + " is not found.");
        return groceryItem;
    }
}
