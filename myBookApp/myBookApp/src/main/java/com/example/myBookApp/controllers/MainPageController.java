package com.example.myBookApp.controllers;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.BooksPageDto;
import com.example.myBookApp.data.dto.CreateBookObj;
import com.example.myBookApp.data.dto.SearchWordDto;
import com.example.myBookApp.data.model.book.books.Book;
import com.example.myBookApp.data.model.book.books.Tags;
import com.example.myBookApp.data.service.books.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Tag(name = "sdf", description = "wef2q3t34q")
public class MainPageController {

    private final BooksRecommendedPageService booksRecommendedPageService;
    private final BooksRecentPageService recentPageService;

    private final BooksPopularPageService booksPopularPageService;
    private final BookTagPageService bookTagPageService;
    private final BookSearchPageService bookSearchPageService;

    @Autowired
    public MainPageController(BooksRecommendedPageService booksRecommendedPageService,
                              BooksRecentPageService recentPageService,
                              BooksPopularPageService booksPopularPageService,
                              BookTagPageService bookTagPageService,
                              BookSearchPageService bookSearchPageService) {
        this.booksRecommendedPageService = booksRecommendedPageService;
        this.recentPageService = recentPageService;
        this.booksPopularPageService = booksPopularPageService;
        this.bookTagPageService = bookTagPageService;
        this.bookSearchPageService = bookSearchPageService;
    }

    @Operation(summary = "return main page index.html")
    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        return "index";
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model,
                                   HttpServletRequest httpServletRequest){
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookSearchPageService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent()
                        .stream()
                        .map(b -> new CreateBookObj(b).getBookDtoObj()).toList());
        return "/search/index";
    }
    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto){
        return new BooksPageDto(bookSearchPageService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent()
                .stream()
                .map(b -> new CreateBookObj(b).getBookDtoObj()).toList());
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

    @ModelAttribute("tagsList")
    public List<Tags> tagsBooks(){
        return bookTagPageService.getListTags();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults(){
        return new ArrayList<>();
    }



}
