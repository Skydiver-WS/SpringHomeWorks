package com.example.myBookApp.data.service;

import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getBooksData() {
        return booksRepository.findAll();
    }
}
