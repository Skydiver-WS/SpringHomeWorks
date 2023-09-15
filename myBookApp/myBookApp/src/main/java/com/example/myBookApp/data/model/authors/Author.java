package com.example.myBookApp.data.model.authors;

import com.example.myBookApp.data.model.book.books.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "author")
    private List<Book> bookList;
    private String photo;
    @Column(nullable = false)
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Override
    public String toString() {
        return firstName + ' ' +
                lastName;
    }
}
