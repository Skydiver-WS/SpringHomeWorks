package com.example.myBookApp.controllers;

import com.example.myBookApp.data.dto.BookDto;
import com.example.myBookApp.data.dto.TagDto;
import com.example.myBookApp.data.model.book.books.Tags;
import com.example.myBookApp.data.service.books.BookTagPageService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TagsPageController {

    private final BookTagPageService bookTagPageService;

    @Autowired
    public TagsPageController(BookTagPageService bookTagPageService) {
        this.bookTagPageService = bookTagPageService;
    }

    @GetMapping("/tag/{tag}")
    public String tagsPage(@PathVariable(value = "tag", required = false) TagDto tag,
                           Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("httpServletRequest", httpServletRequest);
        model.addAttribute("booksList", bookTagPageService.getBooksByTags(0, 5, tag.getTag()));
        model.addAttribute("tagObj", tag);
        return "/tags/index";
    }

    @GetMapping("/books/tag/{tag}")
    public List<BookDto> tagPage(@RequestParam("offset")Integer offset,
                                 @RequestParam("limit") Integer limit,
                                 @PathVariable(value = "tag", required = false) TagDto tag,
                                 Model model){
        model.addAttribute("tagObj", tag);
        return bookTagPageService.getBooksByTags(offset, limit, tag.getTag());
    }

    @ModelAttribute("tagObj")
    public BookDto getBookDto(){
        return new BookDto();
    }
}
