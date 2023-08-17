package com.example.myBookApp.controllers;

import com.example.myBookApp.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherPagesController {
    private final AuthorService authorService;

    @Autowired
    public OtherPagesController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/genres/index.html")
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping("/authors/index.html")
    public String authorsPages(Model model) {
        model.addAttribute("authorList", authorService.getAuthorInfo());
        return "authors/index";
    }

    @GetMapping("/index.html")
    public String mainPage() {
        return "redirect:/bookshop/main";
    }

}
