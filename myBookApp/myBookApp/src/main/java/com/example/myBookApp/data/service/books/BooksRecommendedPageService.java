package com.example.myBookApp.data.service.books;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.CreateBookObj;
import com.example.myBookApp.data.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.ContentHandler;
import java.util.List;

@Service
public class BooksRecommendedPageService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksRecommendedPageService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BookDto> getPageOfBooks(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return booksRepository.findAll(pageable)
                .getContent().stream()
                .map(b -> new CreateBookObj(b).getBookDtoObj()).toList();
    }
}
