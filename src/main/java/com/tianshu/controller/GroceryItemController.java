package com.tianshu.controller;

import com.tianshu.domain.GroceryItem;
import com.tianshu.dto.GroceryItemDto;
import com.tianshu.exception.GroceryItemNotFoundException;
import com.tianshu.exception.GroceryItemsNotFoundByCategoryException;
import com.tianshu.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grocery-item")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public GroceryItemDto createGroceryItem(@Valid @RequestBody GroceryItemDto groceryItemDto){
        return groceryItemService.createGroceryItem(groceryItemDto);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public GroceryItemDto detailGroceryItem(@PathVariable String name) throws GroceryItemNotFoundException {
        return groceryItemService.findGroceryItemByName(name);
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<GroceryItemDto> detailAllGroceryItems(@PathVariable String category) throws GroceryItemsNotFoundByCategoryException {
        return groceryItemService.findAllByCategory(category);
    }

    @GetMapping("/name/{name}/quantity/{quantity}")
    @ResponseStatus(code = HttpStatus.OK)
    public GroceryItemDto consumeGroceryItem(@PathVariable String name, @PathVariable int quantity) throws GroceryItemNotFoundException {
        return groceryItemService.consumeGroceryItem(name,quantity);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public GroceryItemDto updateGroceryItem(@RequestBody GroceryItemDto groceryItemDto) throws GroceryItemNotFoundException {
        return groceryItemService.updateGroceryItem(groceryItemDto);
    }
}
