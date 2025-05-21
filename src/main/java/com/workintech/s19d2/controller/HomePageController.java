package com.workintech.s19d2.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/homepage")
public class HomePageController {

    @GetMapping
    public String welcome(){
        return "welcome";
    }
}
