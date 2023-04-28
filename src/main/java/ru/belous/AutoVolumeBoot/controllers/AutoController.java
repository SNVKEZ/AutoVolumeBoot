package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belous.AutoVolumeBoot.dtos.AutoDTO;
import ru.belous.AutoVolumeBoot.exceptions.DataNotFoundException;
import ru.belous.AutoVolumeBoot.services.AutoService;
import ru.belous.AutoVolumeBoot.entities.Auto;
import ru.belous.AutoVolumeBoot.services.PersonService;
import ru.belous.AutoVolumeBoot.utils.PersonErrorResponse;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class AutoController {
    private final AutoService autoService;
    private final PersonService personService;
    private final SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss a zzz");

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
    public List<AutoDTO> showAutoForPerson(@RequestParam("username") String username){
        return personService.showPersonCars(username);
    }

    @DeleteMapping("/auto/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAutoById(@PathVariable("id") int id){

        try {
            autoService.deleteAutoById(id);
        }catch (Exception e){
            throw new DataNotFoundException();
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(DataNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Data not found",
                formatForDateNow.format(new Date())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
