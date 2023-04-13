package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.exceptions.NotValidDataPerson;
import ru.belous.AutoVolumeBoot.exceptions.PersonNotFoundException;
//import ru.belous.AutoVolumeBoot.security.PersonDetails;
import ru.belous.AutoVolumeBoot.services.PersonService;
import ru.belous.AutoVolumeBoot.utils.PersonErrorResponse;

import javax.validation.Valid;
import java.util.List;

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

//    @GetMapping("/showinfo")
//    public String showInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
//        return "firstPage";
//    }

    @ResponseBody
    @GetMapping("/api")
    public String showApi(){
        return "api";
    }

    @ResponseBody
    @GetMapping("/api/people")
    public List<Person> showPeople(){
        return personService.showAll();
    }
    @ResponseBody
    @GetMapping("/api/people/{id}")
    public Person showPerson(@PathVariable("id") int id){

        return personService.showOne(id);
    }

    @ResponseBody
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Data not found",
                System.currentTimeMillis()
        );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> personException(NotValidDataPerson e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Not valid data",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/newPerson")
    public ResponseEntity<HttpStatus> newPerson(@RequestBody @Valid Person person,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder eror = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError erorr : errors){
                eror.append(erorr.getField()).append("-").append(erorr.getDefaultMessage())
                        .append(";");
            }

            throw new NotValidDataPerson(eror.toString());
        }

        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
