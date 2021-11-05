package com.gremo.springbootconcourse.books.repository;

import com.gremo.springbootconcourse.books.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {

    private Map<String, Book> books = new HashMap<>();

    public Book addBook(Book book){
        if(books.containsKey(book.getIsbn())){
            books.replace(book.getIsbn(), book);
        }else {
            books.put(book.getIsbn(), book);
        }

        return book;
    }

    public Book updateBook(Book book){
        if(books.containsKey(book.getIsbn())){
            books.replace(book.getIsbn(), book);
        }else {
            books.put(book.getIsbn(), book);
        }

        return book;
    }

    public void deleteBook(String isbn){
        if(books.containsKey(isbn)){
            books.remove(isbn);
        }
    }

    public Collection<Book> getAllBooks(){
        return books.values();
    }

    public Book getBook(String isbn){
        return books.getOrDefault(isbn, null);
    }

    public Book searchBook(String author){
        return books.values().stream().filter(b -> b.getAuthor().equals(author)).findAny().orElse(null);
    }
}
