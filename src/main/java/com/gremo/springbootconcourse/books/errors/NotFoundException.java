package com.gremo.springbootconcourse.books.errors;

public class NotFoundException extends Exception{
    public NotFoundException(String s) {
        super(s);
    }
}
