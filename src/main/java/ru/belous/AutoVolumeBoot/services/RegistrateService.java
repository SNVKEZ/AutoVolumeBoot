package ru.belous.AutoVolumeBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;

@Service
public class RegistrateService {
    private final PeopleRepo peopleRepo;

    @Autowired
    public RegistrateService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    @Transactional
    public void registration(Person person){
        peopleRepo.save(person);
    }
}
