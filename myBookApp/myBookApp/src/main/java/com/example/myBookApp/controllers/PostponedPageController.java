package com.example.myBookApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostponedPageController {
    @GetMapping("/postponed")
    public String postponedPage(Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("httpServletRequest", httpServletRequest);
        return "postponed";
    }
}
