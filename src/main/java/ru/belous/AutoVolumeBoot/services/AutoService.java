package ru.belous.AutoVolumeBoot.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.belous.AutoVolumeBoot.dtos.AutoDTO;
import ru.belous.AutoVolumeBoot.entities.Auto;
import ru.belous.AutoVolumeBoot.repositories.AutoRepo;
import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class AutoService {
    private final AutoRepo autoRepo;
    private final PeopleRepo peopleRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public AutoService(AutoRepo autoRepo, PeopleRepo peopleRepo, ModelMapper modelMapper) {
        this.autoRepo = autoRepo;
        this.peopleRepo = peopleRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<AutoDTO> showAll(){
        List<Auto> auto = autoRepo.findAll();
        List<AutoDTO> autoDTOS = new ArrayList<>();
        for (Auto value : auto) {
            autoDTOS.add(convertToAutoDTO(value));
        }
        return autoDTOS;
    }

    @Transactional
    public void addAuto(@Valid Auto auto){
        auto.setOwner(peopleRepo.findById(auto.getOwner().getId()).orElse(null));
        autoRepo.save(auto);
    }

    @Transactional
    public AutoDTO showAutoById(int id){
        return convertToAutoDTO(autoRepo.findById(id).orElse(null));
    }

    @Transactional
    public void deleteAutoById(int id){
        autoRepo.deleteById(id);
    }

    @Transactional
    public void addAutoForPersonByUsername(Auto auto,String username){
        auto.setOwner(peopleRepo.findByUsername(username).orElse(null));
        autoRepo.save(auto);
    }

    @Transactional
    public void changeColorAutoById(int id, String color){
       Auto auto = Objects.requireNonNull(autoRepo.findById(id).orElse(null));
       auto.setColor(color);
        autoRepo.save(auto);
    }
    public AutoDTO convertToAutoDTO(Auto auto){
        return this.modelMapper.map(auto, AutoDTO.class);
    }

}
