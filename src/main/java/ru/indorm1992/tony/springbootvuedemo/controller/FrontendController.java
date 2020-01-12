package ru.indorm1992.tony.springbootvuedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping
    public String getIndex() {
        return "index.html";
    }
}
