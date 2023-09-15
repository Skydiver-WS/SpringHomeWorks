package com.example.myBookApp.data.model.book.books;

import com.example.myBookApp.data.model.authors.Author;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @Column(name = "pub_date", nullable = false)
    private LocalDateTime pubDate;
    @Column(nullable = false, columnDefinition = "SMALLINT")
    private int isBestseller;
    @Column(nullable = false)
    private String slug;
    private String title;
    @Column(nullable = false)
    private String image;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String priceOld;
    private String price;
    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private int discount;
}
