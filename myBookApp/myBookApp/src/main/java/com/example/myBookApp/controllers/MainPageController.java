package com.example.myBookApp.controllers;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.service.books.BooksPopularPageService;
import com.example.myBookApp.data.service.books.BooksRecentPageService;
import com.example.myBookApp.data.service.books.BooksRecommendedPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Tag(name = "sdf", description = "wef2q3t34q")
public class MainPageController {

    private final BooksRecommendedPageService booksRecommendedPageService;
    private final BooksRecentPageService recentPageService;

    private final BooksPopularPageService booksPopularPageService;

    @Autowired
    public MainPageController(BooksRecommendedPageService booksRecommendedPageService,
                              BooksRecentPageService recentPageService,
                              BooksPopularPageService booksPopularPageService) {
        this.booksRecommendedPageService = booksRecommendedPageService;
        this.recentPageService = recentPageService;
        this.booksPopularPageService = booksPopularPageService;
    }

    @Operation(summary = "return main page index.html")
    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        return "index";
    }

    @ModelAttribute("recommendedBooks")
    public List<BookDto> recommendedBooks() {
        return booksRecommendedPageService.getPageOfBooks(0, 6);
    }
    @ModelAttribute("recentBooks")
    public List<BookDto> recentBooks() {
        return recentPageService.getRecentBooks(0, 6);
    }

    @ModelAttribute("popular")
    public List<BookDto> popularBooks(){
        return booksPopularPageService.getPopularBooksRepository(0, 6);
    }

}
