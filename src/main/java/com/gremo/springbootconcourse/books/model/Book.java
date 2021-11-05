package com.gremo.springbootconcourse.books.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

    @NotNull
    @Size(min = 2, message = "isbn should have minimum length of 2")
    private String isbn;

    @NotNull
    @Size(min = 2, message = "author should have minimum length of 2")
    private String author;

    @NotNull
    @Size(min = 2, message = "title should have minimum length of 2")
    private String title;

    @NotNull
    private double price;

    public Book(){}

    public Book(String isbn, String author, String title, double price){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
