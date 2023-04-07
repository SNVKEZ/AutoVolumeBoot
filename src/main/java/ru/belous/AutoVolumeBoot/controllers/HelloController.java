package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.belous.AutoVolumeBoot.security.PersonDetails;

import javax.persistence.Persistence;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "firstPage";
    }

    @GetMapping("/showinfo")
    public String showInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        System.out.println(personDetails.getPerson().toString());
        return "firstPage";
    }
}
