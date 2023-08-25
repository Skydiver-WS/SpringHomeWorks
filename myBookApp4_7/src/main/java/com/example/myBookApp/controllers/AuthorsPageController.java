package com.example.myBookApp.controllers;

import com.example.myBookApp.data.dto.Author;
import com.example.myBookApp.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authors")
public class AuthorsPageController {
    private final AuthorService authorService;

    @Autowired
    public AuthorsPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String authorsPages() {
        return "/authors/index";
    }
    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap(){
        return authorService.getAuthorInfo();
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/";
    }
}
