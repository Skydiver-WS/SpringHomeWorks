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
@Schema(name = "book date", description = "test descriptions", hidden = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id table", description = " test1", hidden = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonIgnore
    @Schema(name = "authors table", description = " test2", hidden = true)
    private Author author;

    @Column(name = "pub_date", nullable = false)
    @Schema(name = "pubDate table", description = " test3", hidden = true)
    private Date pubDate;

    @Column(name = "is_bestseller", nullable = false, columnDefinition = "SMALLINT")
    private Integer isBestseller;


    private String slug;

    private String title;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private Integer priceOld;

    @Column(name = "discount")
    private Integer price;
}
