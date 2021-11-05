package com.gremo.springbootconcourse.books.controller;

import com.gremo.springbootconcourse.books.errors.CustomError;
import com.gremo.springbootconcourse.books.errors.NotFoundException;
import com.gremo.springbootconcourse.books.model.Book;
import com.gremo.springbootconcourse.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(){
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book){

        if(book == null){
            return new ResponseEntity<>(new CustomError("Book cannot be null"), HttpStatus.BAD_REQUEST);
        }
        book = bookService.addBook(book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestBody @Valid Book book, @PathVariable String isbn) throws NotFoundException {

        book = bookService.updateBook(book, isbn);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) throws NotFoundException {

        bookService.deleteBook(isbn);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable String isbn) throws NotFoundException {

        Book book = bookService.getBook(isbn);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBooks(){

        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @RequestMapping(value = "/books/search/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> searchBooks(@PathVariable String author){

        Book book = bookService.searchBook(author);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
