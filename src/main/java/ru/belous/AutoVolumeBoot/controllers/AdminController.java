package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.services.PersonService;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()

    public String adminPage(Model model, @ModelAttribute("person") Person person){
        model.addAttribute("people",personService.showAll());
        return "admins/adminPage";
    }
    @PostMapping("/add")
    public String addAdmin(@ModelAttribute("person") Person person){
        personService.setAdmin(person.getId());
        return "redirect:/admin";
    }
}
