package com.example.myBookApp.data.model.book.books;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tag;
    @OneToMany
    @JoinColumn(name = "list_book_id", referencedColumnName = "id")
    private List<Book> books;
}
