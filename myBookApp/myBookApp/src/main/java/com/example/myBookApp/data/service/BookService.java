package com.example.myBookApp.data.service;

import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public Page<Book> getRecentBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate").descending());
        Page<Book> bookPage = booksRepository.findAll(nextPage);
        List<Book> sortedBooks = bookPage.getContent().stream().sorted(Comparator.comparing(Book::getPubDate)).toList();
        return new PageImpl<>(sortedBooks, nextPage, bookPage.getTotalElements());
    }


}
