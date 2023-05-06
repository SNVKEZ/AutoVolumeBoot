package ru.belous.AutoVolumeBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belous.AutoVolumeBoot.entities.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepo extends JpaRepository<Person,Integer> {
    Optional<Person> findByUsername(String username);
    List<Optional<Person>> findAllByRole(String role);
}
