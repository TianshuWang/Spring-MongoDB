package com.tianshu.exception;

public class GroceryItemNotFoundException extends RuntimeException {
    public GroceryItemNotFoundException(String s) {
        super(s);
    }
}
