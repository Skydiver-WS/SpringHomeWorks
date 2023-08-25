package com.example.myBookApp.controllers;

import com.example.myBookApp.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresPagesController {
    private final AuthorService authorService;

    @Autowired
    public GenresPagesController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/genres/index.html")
    public String genresPage() {
        return "genres/index";
    }



}
