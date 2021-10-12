package com.tianshu.controller;

import com.tianshu.domain.GroceryItem;
import com.tianshu.dto.GroceryItemDto;
import com.tianshu.exception.GroceryItemNotFoundException;
import com.tianshu.exception.GroceryItemsNotFoundByCategoryException;
import com.tianshu.service.GroceryItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequestMapping("/grocery-item")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create a Grocery Item", response = GroceryItemDto.class)
    public GroceryItemDto createGroceryItem(@Valid @RequestBody @ApiParam(value = "Created Grocery Item", required = true) GroceryItemDto groceryItemDto){
        return groceryItemService.createGroceryItem(groceryItemDto);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Find a Grocery Item by Name", response = GroceryItemDto.class)
    public GroceryItemDto detailGroceryItem(@PathVariable String name) throws GroceryItemNotFoundException {
        return groceryItemService.findGroceryItemByName(name);
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Find all Grocery Items by Category")
    public List<GroceryItemDto> detailAllGroceryItems(@PathVariable String category) throws GroceryItemsNotFoundByCategoryException {
        return groceryItemService.findAllByCategory(category);
    }

    @GetMapping("/name/{name}/quantity/{quantity}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Consume a Grocery Item", response = GroceryItemDto.class)
    public GroceryItemDto consumeGroceryItem(@PathVariable String name, @PathVariable @Min(1) Integer quantity) throws GroceryItemNotFoundException {
        return groceryItemService.consumeGroceryItem(name,quantity);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Update a Grocery Item", response = GroceryItemDto.class)
    public GroceryItemDto updateGroceryItem(@RequestBody @ApiParam(value = "Updated Grocery Item", required = true) GroceryItemDto groceryItemDto) throws GroceryItemNotFoundException {
        return groceryItemService.updateGroceryItem(groceryItemDto);
    }
}
