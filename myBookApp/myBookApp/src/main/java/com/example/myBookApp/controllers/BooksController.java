package com.example.myBookApp.controllers;

import com.example.myBookApp.data.dto.BooksPageDto;
import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/recent")
//    public String recentPage(Model model, HttpServletRequest httpServletRequest) {
//        model.addAttribute("httpServletRequest", httpServletRequest);
//        return "/books/recent";
//    }
//
//    @GetMapping("/popular")
//    public String popularPage(Model model, HttpServletRequest httpServletRequest) {
//        model.addAttribute("httpServletRequest", httpServletRequest);
//        return "/books/popular";
//    }


    @GetMapping("/recommended")
    @ResponseBody
    public BooksPageDto getBooksRecommendedPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfBooks(offset, limit).getContent());
    }

    @GetMapping("/recent")
    @ResponseBody
    public BooksPageDto getBooksRecentPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksPageDto booksPageDto = new BooksPageDto(bookService.getRecentBooks(offset, limit).getContent());
        return booksPageDto;
    }

    @GetMapping("/popular")
    @ResponseBody
    public BooksPageDto getBooksPopularPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfBooks(offset, limit).getContent());
    }
}
