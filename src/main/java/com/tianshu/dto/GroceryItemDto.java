package com.tianshu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "All details about the Grocery Item")
public class GroceryItemDto {

    @NotBlank
    @ApiModelProperty(value = "Id of the Grocery Item",name = "id", dataType = "String",required = true)
    private String id;

    @NotBlank
    @ApiModelProperty(value = "Name of the Grocery Item",name = "name", dataType = "String",required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "Stock of the Grocery Item",name = "quantity", dataType = "int", required = true)
    private int quantity;

    @NotBlank
    @ApiModelProperty(value = "Category of the Grocery Item",name = "category", dataType = "String", required = true)
    private String category;

    public GroceryItemDto(String id, String name, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public GroceryItemDto() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
