package com.example.myBookApp.data.service;

import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> getPageOfBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return booksRepository.findAll(nextPage);
    }
}
