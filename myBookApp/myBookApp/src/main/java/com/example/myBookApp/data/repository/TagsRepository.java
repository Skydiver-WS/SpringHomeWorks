package com.example.myBookApp.data.repository;

import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.model.book.books.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {
   Tags getByTag(String tag);
}
