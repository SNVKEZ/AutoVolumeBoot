package ru.belous.AutoVolumeBoot.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belous.AutoVolumeBoot.dtos.AutoDTO;
import ru.belous.AutoVolumeBoot.entities.Auto;
import ru.belous.AutoVolumeBoot.repositories.AutoRepo;
import ru.belous.AutoVolumeBoot.repositories.PeopleRepo;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public void addAuto(Auto auto){
        auto.setOwner(peopleRepo.findById(auto.getOwner().getId()).orElse(null));
        autoRepo.save(auto);
    }

    @Transactional
    public AutoDTO showAutoById(int id){
        return convertToAutoDTO(autoRepo.findById(id).orElse(null));
    }

//    @Transactional
//    public AutoDTO showOne(int id){
//        return convertToAutoDTO(autoRepo.findByPersonId(id));
//    }

    public AutoDTO convertToAutoDTO(Auto auto){
        return this.modelMapper.map(auto, AutoDTO.class);
    }

}
