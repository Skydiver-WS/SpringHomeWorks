package com.example.myBookApp.data.service.books;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.BooksPageDto;
import com.example.myBookApp.data.dto.CreateBookObj;
import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BooksPopularPageService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksPopularPageService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BookDto> getPopularBooksRepository(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return booksRepository.getBookByOrderByPopularDesc(pageable).getContent()
                .stream().map(b -> new CreateBookObj(b).getBookDtoObj()).sorted(Comparator.comparing(BookDto::getRating).reversed()).toList();
    }
}
