package ru.belous.AutoVolumeBoot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.dtos.AuthDTO;
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
@Validated
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrateService registrateService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrateService registrateService, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.registrateService = registrateService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public Map<String,String> performReg(@Valid @RequestBody PersonDTO personDTO,
                                         BindingResult bindingResult){
        Person person = convertToPerson(personDTO);
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()) return Map.of("message","user with this name exists");

        registrateService.registration(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Collections.singletonMap("jwt-token",token);
    }

    @PostMapping("/login")
    public Map<String , String> performLogin(@RequestBody @Valid AuthDTO dto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
        try {
            authenticationManager.authenticate(authToken);
        }catch (BadCredentialsException e){
            return Map.of("message","not right login or password");
        }
        String token = jwtUtil.generateToken(dto.getUsername());
        return Collections.singletonMap("jwt-token",token);
    }

    private Person convertToPerson(PersonDTO personDTO){
        return this.modelMapper.map(personDTO, Person.class);
    }
}
