package ru.vyacheslavkozlov.firstrunday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/runday")
public class MainController {

    @GetMapping
    public String goHello(){
        return "hello";
    }

}
