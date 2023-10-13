package com.example.myBookApp.data.service.books;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.CreateBookObj;
import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.repository.BooksRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@Service
public class BooksRecentPageService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksRecentPageService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BookDto> getRecentBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate"));
        Page<Book> bookPage = booksRepository.findAll(nextPage);
        return bookPage.getContent().stream()
                .sorted(Comparator.comparing(Book::getPubDate)).toList()
                .stream().map(b -> new CreateBookObj(b).getBookDtoObj()).toList();
    }

    public List<BookDto> getRecentBooks(Integer offset, Integer limit, String fromDate, String toDate){
        Date dateFrom = Date.valueOf(convertDate(fromDate));
        Date dateTo = Date.valueOf(convertDate(toDate));
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate"));
        Page<Book> bookPage = booksRepository.getBookByPubDateAfterAndPubDateBeforeOrderByPubDate(dateFrom, dateTo, nextPage);
        return bookPage.getContent().stream().map(b -> new CreateBookObj(b).getBookDtoObj()).toList();
    }

    @SneakyThrows
    private String convertDate (String date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date d = format.parse(date);
        format.applyPattern("yyyy-MM-dd");
        return format.format(d);
    }

}
