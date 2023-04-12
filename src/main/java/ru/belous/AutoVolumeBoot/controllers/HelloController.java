package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.exceptions.PersonNotFoundException;
import ru.belous.AutoVolumeBoot.security.PersonDetails;
import ru.belous.AutoVolumeBoot.services.PersonService;
import ru.belous.AutoVolumeBoot.utils.PersonErrorResponse;

import javax.persistence.Access;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HelloController {

    private final PersonService personService;


    @Autowired
    public HelloController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/showinfo")
    public String showInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        return "firstPage";
    }

    @ResponseBody
    @GetMapping("/api")
    public String showApi(){
        return "api";
    }

    @ResponseBody
    @GetMapping("/api/peolpe")
    public List<Person> showPeople(){
        return personService.showAll();
    }
    @ResponseBody
    @ExceptionHandler
    @GetMapping("/api/peolpe/{id}")
    public Person showPerson(@PathVariable("id") int id){
        return personService.showOne(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Data not found",
                System.currentTimeMillis()
        );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
