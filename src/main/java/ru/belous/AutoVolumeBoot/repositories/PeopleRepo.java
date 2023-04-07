package ru.belous.AutoVolumeBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belous.AutoVolumeBoot.entities.Person;

@Repository
public interface PeopleRepo extends JpaRepository<Person,Integer> {
}
