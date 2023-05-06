package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.services.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController extends ExceptionHandlerController {

    private final PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/show")
    public List<Optional<Person>> adminPage() {
        return personService.showAllAdmins();

    }

    @PatchMapping("/add")
    public ResponseEntity<HttpStatus> addAdmin(@RequestParam("id_person") int id_person){
        personService.setAdmin(id_person);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
