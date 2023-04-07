package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class FirstPage {

    @GetMapping()
    public String loginMethod(){
        return "firstPage";
    }

    @GetMapping("/logonPage")
    public String goodByePage() {
        return "logonPage";
    }
}
