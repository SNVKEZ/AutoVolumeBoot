package ru.belous.AutoVolumeBoot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.dtos.PersonDTO;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.security.JWTUtil;
import ru.belous.AutoVolumeBoot.services.RegistrateService;
import ru.belous.AutoVolumeBoot.utils.PersonValidator;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrateService registrateService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrateService registrateService, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.personValidator = personValidator;
        this.registrateService = registrateService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
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
    public Map<String,String> performReg(@RequestBody @Valid PersonDTO personDTO,
                                         BindingResult bindingResult){
        Person person = convertToPerson(personDTO);
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()) return Map.of("message","operation not run");

        registrateService.registration(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Collections.singletonMap("jwt-token",token);
    }

    private Person convertToPerson(PersonDTO personDTO){
        return this.modelMapper.map(personDTO, Person.class);
    }
}
