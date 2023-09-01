package com.example.myBookApp.controllers;

import com.example.myBookApp.data.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenresPagesController {
    private final AuthorService authorService;

    @Autowired
    public GenresPagesController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String genresPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        return "genres/index";
    }
}
