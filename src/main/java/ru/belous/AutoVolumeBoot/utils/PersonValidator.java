package ru.belous.AutoVolumeBoot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.services.PeopleService;
import ru.belous.AutoVolumeBoot.services.PersonService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    /*@Autowired
    public PersonValidator(PersonService peopleService) {
        this.peopleService = peopleService;
    }
*/
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try {
            peopleService.loadUserByUsername(person.getUsername());
        } catch (NullPointerException nullPointerException) {
            return;
        }

        errors.rejectValue("username","","есть такой");
    }
}
