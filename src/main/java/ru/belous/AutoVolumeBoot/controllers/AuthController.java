package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.services.RegistrateService;
import ru.belous.AutoVolumeBoot.utils.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrateService registrateService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrateService registrateService) {
        this.personValidator = personValidator;
        this.registrateService = registrateService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performReg(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()) return "/auth/registration";
        registrateService.registration(person);
        return "redirect:/auth/login";
    }
}
