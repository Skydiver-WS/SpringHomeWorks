package com.example.myBookApp.data.service.books;

import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookSearchPageService {
    private final BooksRepository booksRepository;

    @Autowired
    public BookSearchPageService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable pageable = PageRequest.of(offset, limit);
        return booksRepository.findBookByTitleContaining(searchWord, pageable);
    }
}
