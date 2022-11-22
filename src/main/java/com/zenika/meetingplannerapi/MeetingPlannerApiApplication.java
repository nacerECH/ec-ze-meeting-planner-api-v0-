package com.zenika.meetingplannerapi;

import com.zenika.meetingplannerapi.Dtos.EquipementDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.Services.Implimentations.EquipementService;
import com.zenika.meetingplannerapi.Services.Implimentations.SalleService;
import com.zenika.meetingplannerapi.entities.Equipement;
import com.zenika.meetingplannerapi.entities.Salle;
import com.zenika.meetingplannerapi.entities.enumerations.EtatSalle;
import com.zenika.meetingplannerapi.mappers.EquipementMapper;
import com.zenika.meetingplannerapi.repositories.EquipementRepository;
import com.zenika.meetingplannerapi.repositories.SalleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@EnableWebMvc

public class MeetingPlannerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingPlannerApiApplication.class, args);
    }




    @Bean
    CommandLineRunner start (SalleService salleService){

        return  args -> {

            // enregistrer la liste des salles

            Equipement equipementDtoN = new Equipement("Neant", new ArrayList<>());
            Equipement equipementDtoE = new Equipement("Ecran", new ArrayList<>());
            Equipement equipementDtoP = new Equipement("Pieuvre", new ArrayList<>());
            Equipement equipementDtoT = new Equipement("Tableau", new ArrayList<>());
            Equipement equipementDtoW = new Equipement("Webcam", new ArrayList<>());


            List<Equipement> equipementListN = new ArrayList<>();
            equipementListN.add(equipementDtoN);
            List<Equipement> equipementListE = new ArrayList<>();
            equipementListE.add(equipementDtoE);
            List<Equipement> equipementListP = new ArrayList<>();
            equipementListP.add(equipementDtoP);
            List<Equipement> equipementListT = new ArrayList<>();
            equipementListT.add(equipementDtoT);
            List<Equipement> equipementListW = new ArrayList<>();
            equipementListW.add(equipementDtoW);
            List<Equipement> equipementListEW = new ArrayList<>();
            equipementListEW.add(equipementDtoE);
            equipementListEW.add(equipementDtoW);
            List<Equipement> equipementListEWP = new ArrayList<>();
            equipementListEWP.add(equipementDtoE);
            equipementListEWP.add(equipementDtoW);
            equipementListEWP.add(equipementDtoP);
            List<Equipement> equipementListEP = new ArrayList<>();
            equipementListEP.add(equipementDtoE);
            equipementListEP.add(equipementDtoP);


            salleService.AddSalle(new Salle("E10001",23, EtatSalle.valueOf("LIBRE"),equipementListN,null));
            salleService.AddSalle(new Salle("E10002",10, EtatSalle.valueOf("LIBRE"),equipementListE,null));
            salleService.AddSalle(new Salle("E10003",8, EtatSalle.valueOf("LIBRE"),equipementListP,null));
            salleService.AddSalle(new Salle("E10004",4, EtatSalle.valueOf("LIBRE"),equipementListT,null));
            salleService.AddSalle(new Salle("E20001",4, EtatSalle.valueOf("LIBRE"),equipementListN,null));
            salleService.AddSalle(new Salle("E20002",15, EtatSalle.valueOf("LIBRE"),equipementListEW,null));
            salleService.AddSalle(new Salle("E20003",7, EtatSalle.valueOf("LIBRE"),equipementListN,null));
            salleService.AddSalle(new Salle("E20004",9, EtatSalle.valueOf("LIBRE"),equipementListT,null));
            salleService.AddSalle(new Salle("E30001",13, EtatSalle.valueOf("LIBRE"),equipementListEWP,null));
            salleService.AddSalle(new Salle("E30002",8, EtatSalle.valueOf("LIBRE"),equipementListN,null));
            salleService.AddSalle(new Salle("E30003",9, EtatSalle.valueOf("LIBRE"),equipementListEP,null));
            salleService.AddSalle(new Salle("E30004",4, EtatSalle.valueOf("LIBRE"),equipementListN,null));


        };
    }

}
