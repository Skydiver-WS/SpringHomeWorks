package com.example.myBookApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPageController {
    @GetMapping("/about")
    public String aboutPage(Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("httpServletRequest", httpServletRequest);
        return "about";
    }
}
