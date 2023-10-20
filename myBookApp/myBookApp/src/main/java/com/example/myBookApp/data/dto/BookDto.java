package com.example.myBookApp.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Integer id;
    private String slug;
    private String authors;
    private String image;
    private String title;
    private Integer discount;
    private boolean isBestseller;
    private Integer rating;
    private String status;
    private Integer price;
    private Integer discountPrice; //В JS так почему-то назвали старую цену
}
