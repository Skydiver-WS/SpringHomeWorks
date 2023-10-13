package com.example.myBookApp.data.model.book.books;

import com.example.myBookApp.data.model.authors.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
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
    @JsonIgnore
    private Author author;

    //    @Column(name = "pub_date", nullable = false)
    @Column(name = "pub_date")
    private Date pubDate;

    //    @Column(name = "is_bestseller", nullable = false, columnDefinition = "SMALLINT")
    @Column(name = "is_bestseller")
    private Integer isBestseller;

    //    @Column(nullable = false)
    private String slug;

    private String title;

    private String image;

//    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private Integer priceOld;

    //    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private int discount;

    //    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private Integer rating;

    //    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private Integer popular;
}
