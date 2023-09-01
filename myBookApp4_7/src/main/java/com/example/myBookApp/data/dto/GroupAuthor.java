package com.example.myBookApp.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GroupAuthor {
    private String index;
    private List<Author> listAuthor;
}
