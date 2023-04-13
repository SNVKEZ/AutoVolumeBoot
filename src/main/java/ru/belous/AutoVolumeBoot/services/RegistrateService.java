//package ru.belous.AutoVolumeBoot.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.belous.AutoVolumeBoot.entities.Person;
//import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;
//
//@Service
//public class RegistrateService {
//    private final PeopleRepo peopleRepo;
//    private final PasswordEncoder passwordEncoder;
//    @Autowired
//    public RegistrateService(PeopleRepo peopleRepo, PasswordEncoder passwordEncoder) {
//        this.peopleRepo = peopleRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Transactional
//    public void registration(Person person){
//        person.setPassword(passwordEncoder.encode(person.getPassword()));
//        person.setRole("ROLE_USER");
//        peopleRepo.save(person);
//    }
//}
