package ru.belous.AutoVolumeBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belous.AutoVolumeBoot.entities.Auto;

@Repository
public interface AutoRepo extends JpaRepository<Auto,Integer> {
}
