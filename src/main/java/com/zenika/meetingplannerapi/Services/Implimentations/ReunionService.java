package com.zenika.meetingplannerapi.Services.Implimentations;


import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.Services.Interfaces.IReunionService;
import com.zenika.meetingplannerapi.entities.Reunion;

import com.zenika.meetingplannerapi.entities.Salle;
import com.zenika.meetingplannerapi.entities.enumerations.EtatSalle;
import com.zenika.meetingplannerapi.exceptions.SaveReunionException;
import com.zenika.meetingplannerapi.mappers.ReunionMapper;
import com.zenika.meetingplannerapi.repositories.ReunionRepository;
import com.zenika.meetingplannerapi.repositories.SalleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j

public class ReunionService implements IReunionService {


    //--------- L'Injection des dependances
    private SalleService salleService;
    private ReunionMapper reunionMapper;
    private ReunionRepository reunionRepository;


    public ReunionService(SalleService salleService, ReunionMapper reunionMapper, ReunionRepository reunionRepository) {
        this.salleService = salleService;
        this.reunionMapper = reunionMapper;
        this.reunionRepository = reunionRepository;
    }

    //--------------------------------------------------------



    // Cette fonction pour choisir la meilleure salle parmi les salles convenables en mettant comme critere la capacite des salles
    @Override
    public SalleDto GetMeilleureSalle(ReunionDto reunionDto) throws SaveReunionException {
        List<SalleDto> sallesConvenables = salleService.GetSallesConvenables(reunionDto);
        if (sallesConvenables.isEmpty()) {
            return null;
        }
        SalleDto salleDto = sallesConvenables.stream().max(Comparator.comparingInt(SalleDto::getCapacite)).get();
        reunionDto.setSalle_name(salleDto.getNom());
        SaveReunion(reunionDto);
        return salleDto;
    }

    @Override
    public Reunion SaveReunion(ReunionDto reunionDto) throws SaveReunionException {

        if(reunionDto.getSalle_name() == null){throw new SaveReunionException("La réunion ne peut être enregistrée que si elle a un nom de salle."); }

        Reunion reunion = reunionMapper.FromReunionDtoToReunion(reunionDto);
        salleService.GetSalleByName(reunionDto.getSalle_name()).setReunion(reunion);
        salleService.GetSalleByName(reunionDto.getSalle_name()).setEtatSalle(EtatSalle.RESERVEE);
        reunion.setSalle(salleService.GetSalleByName(reunionDto.getSalle_name()));
       return reunionRepository.save(reunion);
    }

    @Override
    public List<ReunionDto> GetReunionsPlanifiees() {
        return reunionRepository.findAll().stream().map(reunion -> reunionMapper.FromReunionToReunionDto(reunion)).collect(Collectors.toList());
    }


}