package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.dtos.AutoDTO;
import ru.belous.AutoVolumeBoot.exceptions.DataNotFoundException;
import ru.belous.AutoVolumeBoot.services.AutoService;
import ru.belous.AutoVolumeBoot.entities.Auto;
import ru.belous.AutoVolumeBoot.services.PersonService;
import ru.belous.AutoVolumeBoot.utils.CatchThrowOnErrorInBindingResult;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(path = "/auto")
public class AutoController extends ExceptionHandlerController {
    private final AutoService autoService;
    private final PersonService personService;
    private final SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss a zzz");

    @Autowired
    public AutoController(AutoService autoService, PersonService personService) {
        this.autoService = autoService;
        this.personService = personService;
    }

    @GetMapping("/show")
    public List<AutoDTO> showAutos(){
        return autoService.showAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addAuto(@RequestBody @Valid Auto auto){
        autoService.addAuto(auto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public AutoDTO showPersonAutos(@PathVariable("id") int id){
        try {
            autoService.showAutoById(id);
        }catch (IllegalArgumentException e){
            throw new DataNotFoundException();
        }
        return autoService.showAutoById(id);
    }

    @GetMapping("/person/showauto")
    public List<AutoDTO> showAutoForPerson(@RequestParam("username") String username){
        return personService.showPersonCars(username);
    }

    @PostMapping("/person/{username}/auto/add")
    public ResponseEntity<HttpStatus> addAutoForPersonById(@PathVariable("username") String username,
                                                           @RequestBody @Valid Auto auto,
                                                           BindingResult bindingResult){
        new CatchThrowOnErrorInBindingResult().catchNotValidException(bindingResult);
        autoService.addAutoForPersonByUsername(auto,username);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/person/{username}/delete")
    public ResponseEntity<HttpStatus> deletePersonByUsername(@PathVariable("username") String username){
        try {
            personService.deletePersonByUsername(username);
        }catch (Exception e){
            throw new DataNotFoundException();
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/color/change")
    public ResponseEntity<HttpStatus> changeColorAuto(@PathVariable("id") int id,
                                                      @RequestParam("color") String color){
        autoService.changeColorAutoById(id,color);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAutoById(@PathVariable("id") int id){
        try {
            autoService.deleteAutoById(id);
        }catch (EmptyResultDataAccessException e){
            throw new DataNotFoundException();
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
