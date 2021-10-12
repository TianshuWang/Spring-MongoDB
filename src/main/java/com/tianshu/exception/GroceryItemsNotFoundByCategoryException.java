package com.tianshu.exception;

public class GroceryItemsNotFoundByCategoryException extends RuntimeException {
    public GroceryItemsNotFoundByCategoryException(String s) {
        super(s);
    }
}
