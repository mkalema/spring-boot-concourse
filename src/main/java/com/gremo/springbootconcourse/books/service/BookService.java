package com.gremo.springbootconcourse.books.service;

import com.gremo.springbootconcourse.books.errors.NotFoundException;
import com.gremo.springbootconcourse.books.model.Book;
import com.gremo.springbootconcourse.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.addBook(book);
    }

    public Book updateBook(Book book, String isbn) throws NotFoundException {
        Book bk = bookRepository.getBook(isbn);
        if(bk == null){
            throw new NotFoundException("Book with isbn " + bk.getIsbn() + " is not found");
        }
        return bookRepository.updateBook(book);
    }

    public void deleteBook(String isbn) throws NotFoundException {
        Book bk = bookRepository.getBook(isbn);
        if(bk == null){
            throw new NotFoundException("Book with isbn " + bk.getIsbn() + " is not found");
        }
        bookRepository.deleteBook(isbn);
    }

    public Book getBook(String isbn) throws NotFoundException {
        Book bk = bookRepository.getBook(isbn);
        if(bk == null){
            throw new NotFoundException("Book with isbn " + isbn + " is not found");
        }
        return bk;
    }

    public Collection<Book> getAllBook(){
        return bookRepository.getAllBooks();
    }

    public Book searchBook(String author){
        return bookRepository.searchBook(author);
    }
}
