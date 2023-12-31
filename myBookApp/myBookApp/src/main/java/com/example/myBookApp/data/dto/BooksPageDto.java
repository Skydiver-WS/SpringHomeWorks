package com.example.myBookApp.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BooksPageDto {
    private Integer count;
    private List<BookDto> books;

    public BooksPageDto(List<BookDto> books) {
        this.books = books;
    }
}
