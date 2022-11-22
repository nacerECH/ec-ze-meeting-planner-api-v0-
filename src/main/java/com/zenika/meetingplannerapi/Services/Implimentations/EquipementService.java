package com.zenika.meetingplannerapi.Services.Implimentations;

import com.zenika.meetingplannerapi.Services.Interfaces.IEquipementService;
import com.zenika.meetingplannerapi.entities.Equipement;

import com.zenika.meetingplannerapi.repositories.EquipementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class EquipementService implements IEquipementService {

    private EquipementRepository equipementRepository;

    public EquipementService(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }




    @Override
    public List<Equipement> SaveEquipements(List<Equipement> equipements) {
         return equipementRepository.saveAll(equipements);
    }
}
