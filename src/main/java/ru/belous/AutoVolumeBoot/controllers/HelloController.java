package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping()
    public String helloWorld(){
        return "hello";
    }
}