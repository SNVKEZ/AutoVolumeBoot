package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.dtos.AutoDTO;
import ru.belous.AutoVolumeBoot.services.AutoService;
import ru.belous.AutoVolumeBoot.entities.Auto;
import ru.belous.AutoVolumeBoot.services.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AutoController {
    private final AutoService autoService;
    private final PersonService personService;

    @Autowired
    public AutoController(AutoService autoService, PersonService personService) {
        this.autoService = autoService;
        this.personService = personService;
    }

    @GetMapping("/auto/show")
    public List<AutoDTO> showAutos(){
        return autoService.showAll();
    }

    @PostMapping("/auto/add")
    public ResponseEntity<HttpStatus> addAuto(@RequestBody @Valid Auto auto){
        autoService.addAuto(auto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/auto/show/{id}")
    public AutoDTO showPersonAutos(@PathVariable("id") int id){
        return autoService.showAutoById(id);
    }

    @GetMapping("/person/showauto")
    public List<AutoDTO> showAutoForPerson(@RequestParam("id") int id){
        return personService.showPersonCars(id);
    }
}
