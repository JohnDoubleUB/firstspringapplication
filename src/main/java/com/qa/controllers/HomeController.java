package com.qa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Rest contoller makes this available to spring
//Rest controller is the new controller
//Controller is the old one and would require a data type conversion type in the function itself
@RestController
public class HomeController {

    //We cant test these the same way!
    //When someone hits a specific address this happens
    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

}
