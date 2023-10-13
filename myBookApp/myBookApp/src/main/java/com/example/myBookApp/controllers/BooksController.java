package com.example.myBookApp.controllers;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.BooksPageDto;
import com.example.myBookApp.data.service.books.BooksPopularPageService;
import com.example.myBookApp.data.service.books.BooksRecentPageService;
import com.example.myBookApp.data.service.books.BooksRecommendedPageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksRecentPageService booksRecentPageService;
    private final BooksRecommendedPageService booksRecommendedPageService;

    private final BooksPopularPageService booksPopularPageService;

    @Autowired
    public BooksController(BooksRecentPageService booksRecentPageService,
                           BooksRecommendedPageService booksRecommendedPageService,
                           BooksPopularPageService booksPopularPageService) {
        this.booksRecentPageService = booksRecentPageService;
        this.booksRecommendedPageService = booksRecommendedPageService;
        this.booksPopularPageService = booksPopularPageService;
    }

    @GetMapping(path = "/recommended", params = {"offset", "limit"})
    @ResponseBody
    public BooksPageDto getBooksRecommendedPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksRecommendedPageService.getPageOfBooks(offset, limit));
    }

    @GetMapping("/recent")
    public String recentPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("booksList", booksRecentPageService.getRecentBooks(0, 20));
        return "/books/recent";
    }

    @GetMapping(path = "/recent", params = {"offset", "limit"})
    @ResponseBody
    public BooksPageDto getBooksRecentPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksRecentPageService.getRecentBooks(offset, limit));
    }

    @GetMapping(path = "/recent", params = {"offset", "limit", "from", "to"})
    @ResponseBody
    public BooksPageDto getBooksRecentPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestParam("from") String fromDate,
                                           @RequestParam("to") String toDate) {
        return new BooksPageDto(booksRecentPageService.getRecentBooks(offset, limit, fromDate, toDate));
    }

    @GetMapping("/popular")
    public String popularPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("booksList", booksPopularPageService.getPopularBooksRepository(0, 20));
        return "/books/popular";
    }

    @GetMapping(path = "/popular", params = {"offset", "limit"})
    @ResponseBody
    public BooksPageDto getBooksPopularPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksPopularPageService.getPopularBooksRepository(offset, limit));
    }
}
