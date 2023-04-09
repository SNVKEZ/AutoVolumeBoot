package ru.belous.AutoVolumeBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;
import ru.belous.AutoVolumeBoot.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {
    private final PeopleRepo peopleRepo;
    @Autowired
    public PersonService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepo.findByUsername(username);
        if(person.isEmpty()) throw new UsernameNotFoundException("not user");

        return new PersonDetails(person.get());
    }
    @Transactional
    public void setAdmin(int id){
        Person person = peopleRepo.findById(id).orElse(null);
        person.setRole("ROLE_ADMIN");
        peopleRepo.save(person);
    }
    @Transactional
    public List<Person> show(){
       return peopleRepo.findAll();
    }
}
