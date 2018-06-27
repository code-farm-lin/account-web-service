package com.bank.account.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String home1() {
        return "/home";
    }


        @GetMapping("/login")
    public String login() {
        return "/login";
    }


    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "/contact";
    }

//
//    @GetMapping("/403")
//    public String error403() {
//        return "/error/403";
//    }

}