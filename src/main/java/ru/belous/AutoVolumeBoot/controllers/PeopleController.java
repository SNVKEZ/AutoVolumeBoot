package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.dtos.PersonDTO;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.exceptions.DataNotFoundException;
import ru.belous.AutoVolumeBoot.services.PersonService;
import java.util.List;

@RestController
@RequestMapping(path = "/people")
public class PeopleController extends ExceptionHandlerController {
    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/show/all")
    public List<Person> showPeople(){
        return personService.showAll();
    }

    @GetMapping("/show/{username}")
    public PersonDTO showPersonByUsername(@PathVariable("username") String username){
        try {
            PersonDTO personDTO = personService.showOneDTO(username);
        }catch (IllegalArgumentException e){
            throw new DataNotFoundException();
        }
        return personService.showOneDTO(username);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<HttpStatus> deletePersonByUsername(@PathVariable("username") String username){
        try {
            personService.deletePersonByUsername(username);
        }catch (NullPointerException | UsernameNotFoundException e){
            throw new DataNotFoundException();
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/change/year/{username}")
    public ResponseEntity<HttpStatus> changeYearOfBirthOfPersonByUsername(@PathVariable("username") String username,
                                                                          @RequestParam("year") int year){
        personService.changeYearOfBirth(username, year);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
