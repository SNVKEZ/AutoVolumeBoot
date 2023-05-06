package ru.belous.AutoVolumeBoot.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.belous.AutoVolumeBoot.dtos.AutoDTO;
import ru.belous.AutoVolumeBoot.dtos.PersonDTO;
import ru.belous.AutoVolumeBoot.entities.Auto;
import ru.belous.AutoVolumeBoot.entities.Person;
import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;
import ru.belous.AutoVolumeBoot.security.PersonDetails;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Validated
public class PersonService implements UserDetailsService {
    private final PeopleRepo peopleRepo;
    private final ModelMapper modelMapper;
    private final AutoService autoService;
    @Autowired
    public PersonService(PeopleRepo peopleRepo, ModelMapper modelMapper, AutoService autoService) {
        this.peopleRepo = peopleRepo;
        this.modelMapper = modelMapper;
        this.autoService = autoService;
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
        Objects.requireNonNull(person).setRole("ROLE_ADMIN");
        peopleRepo.save(person);
    }
    @Transactional
    public List<Person> showAll(){
       return peopleRepo.findAll();
    }

    @Transactional
    public PersonDTO showOneDTO(int id){
        Person person = peopleRepo.findById(id).orElse(null);
        PersonDTO personDTO = convertToPersonDTO(person);
        return personDTO;
    }

    @Transactional
    public List<Optional<Person>> showAllAdmins(){
        return peopleRepo.findAllByRole("ROLE_ADMIN");
    }
    @Transactional
    public void save(@Valid Person person){
        peopleRepo.save(person);
    }

    @Transactional
    public List<AutoDTO> showPersonCars(String username){
        Person person = peopleRepo.findByUsername(username).orElse(null);
        List<Auto> listAuto= Objects.requireNonNull(person).getAutos();
        List<AutoDTO> listAutoDTO = new ArrayList<>();
        for(Auto value : listAuto){
            listAutoDTO.add(autoService.convertToAutoDTO(value));
        }
        return listAutoDTO;
    }

    @Transactional
    public void deletePersonByUsername(String username){
        peopleRepo.delete(Objects.requireNonNull(peopleRepo.findByUsername(username).orElse(null)));
    }

    private PersonDTO convertToPersonDTO(Person person){
        return this.modelMapper.map(person, PersonDTO.class);
    }
}
