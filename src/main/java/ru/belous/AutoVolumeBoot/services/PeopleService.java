package ru.belous.AutoVolumeBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.enums.State;
import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepo peopleRepo;

    @Autowired
    public PeopleService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    public List<Person> findAll(){
        return peopleRepo.findAll();
    }

    public Person findOne(int id){
        return peopleRepo.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person){
        person.setCreatedAt(new Date());
        int i = (int) (Math.random() *3);
        System.out.println(i);
        if(i==1) {
            person.setState(State.RICH);
        }else{
            person.setState(State.AVERAGE);
        }
        peopleRepo.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        peopleRepo.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepo.deleteById(id);
    }
}
