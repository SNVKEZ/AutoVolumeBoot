package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
/*
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {

        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(@NotNull Model model, @ModelAttribute("person") Person person ){
        model.addAttribute("people",personDAO.index());
        return "admins/adminPage";
    }

    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person") @NotNull Person person){
        System.out.println(person.getId());

        return "redirect:/people";
    }*/
}
