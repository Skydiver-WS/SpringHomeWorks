package com.example.myBookApp.data.dto;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String author;
    private String title;
    private String priceOld;
    private String price;
}
