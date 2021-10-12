package com.tianshu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GroceryItemDto {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private int quantity;

    @NotBlank
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
