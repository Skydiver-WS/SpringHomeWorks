package com.example.myBookApp.data.repository;

import com.example.myBookApp.data.model.book.books.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Page<Book> getBookByPubDateAfterAndPubDateBeforeOrderByPubDate(Date pubDateAfter, Date pubDateBefore, Pageable pageable);

    Page<Book> getBookByOrderByPopularDesc(Pageable pageable);
}
