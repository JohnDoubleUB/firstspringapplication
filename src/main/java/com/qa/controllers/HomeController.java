package com.qa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Rest contoller makes this available to spring

@RestController
public class HomeController {

    //When someone hits a specific address this happens
    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

}
