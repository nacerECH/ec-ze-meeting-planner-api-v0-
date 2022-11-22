package com.zenika.meetingplannerapi.Services.Implimentations;

import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.Services.Interfaces.ISalleService;
import com.zenika.meetingplannerapi.entities.Equipement;
import com.zenika.meetingplannerapi.entities.Salle;
import com.zenika.meetingplannerapi.entities.enumerations.EtatSalle;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import com.zenika.meetingplannerapi.mappers.SalleMapper;
import com.zenika.meetingplannerapi.repositories.SalleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@EnableScheduling
public class SalleService implements ISalleService {

    private SalleRepository salleRepository;
    private SalleMapper salleMapper;
    private EquipementService equipementService;

    public SalleService(SalleRepository salleRepository, SalleMapper salleMapper, EquipementService equipementService) {
        this.salleRepository = salleRepository;
        this.salleMapper = salleMapper;
        this.equipementService = equipementService;
    }

    //----------------------------------------------------------

    @Override
    public Salle GetSalleByName(String salle_name) {
        return salleRepository.findById(salle_name).orElse(null);
    }

    @Override
    public List<Salle> GetSallesDisponibles() {
        return salleRepository.GetSallesDisponibles();
    }

    @Override
    public List<SalleDto> GetSallesConvenables(ReunionDto reunionDto) {
        // on filtre les salles disponibles selon les specifications 5 et 6:
        return GetSallesDisponibles().stream()
                .filter(s -> (int)(s.getCapacite()*0.7)  >= reunionDto.getNombre_personnes_conviees() && CheckIfReunionTypeAssuree(s,reunionDto.getTypeReunion()))
                .map(s->salleMapper.FromSalleToSalleDto(s))
                .collect(Collectors.toList());
    }


    @Override
    public boolean CheckIfReunionTypeAssuree(Salle salle, TypeReunion typeReunion) {
        List<String> equip = salle.getEquipements().stream().map(equipement -> equipement.getNom()).collect(Collectors.toList());

        if(typeReunion ==  TypeReunion.VC && equip.contains("Ecran") && equip.contains("Pieuvre") && equip.contains("Webcam")  ){
            return true;
        }
        if(typeReunion ==  TypeReunion.SPEC && equip.contains("Tableau") ){
            return true;
        }
        if(typeReunion ==  TypeReunion.RS && salle.getCapacite()>3  ){
            return true;
        }
        if(typeReunion ==  TypeReunion.RC && equip.contains("Tableau") && equip.contains("Ecran") && equip.contains("Pieuvre")  ){
            return true;
        }
        return false;
    }


    @Override
    public Salle AddSalle(Salle salle) {
        List<Equipement> equipements = salle.getEquipements();
        salle.setEquipements(null);
        salleRepository.save(salle);
        equipements.stream().map(equipement -> equipement.getSalles().add(salle));
        equipementService.SaveEquipements(equipements);
        salle.setEquipements(equipements);
        return salleRepository.save(salle);
    }


    @Scheduled(fixedDelay = 1000)
    public void UpdateSallesAvailability() {

        Calendar rightNow = Calendar.getInstance();
        int hour_rightNow = rightNow.get(Calendar.HOUR_OF_DAY);
        for ( Salle salle : salleRepository.GetSallesNonDisponibles()  ) {

                if (hour_rightNow - salle.getReunion().getCreneau() >= 1 ) {
                    salle.setEtatSalle(EtatSalle.EN_NETTOYAGE);
                    log.info("Salle " + salle.getNom() + "  en cours de nettoyage");
                    salleRepository.save(salle);
                    if (hour_rightNow - salle.getReunion().getCreneau() >= 2) {
                        log.info("Salle " + salle.getNom() + " est désormais libre");
                        salle.setEtatSalle(EtatSalle.LIBRE);
                        salle.getReunion().setSalle(null);
                        salle.setReunion(null);
                        salleRepository.save(salle);

                    }

                }

        }
    }

}
