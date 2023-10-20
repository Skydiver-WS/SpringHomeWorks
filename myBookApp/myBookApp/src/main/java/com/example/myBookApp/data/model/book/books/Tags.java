package com.example.myBookApp.data.model.book.books;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tag;

    @ManyToMany(mappedBy = "listTags")
    private Set<Book> listBooks = new HashSet<>();
}
