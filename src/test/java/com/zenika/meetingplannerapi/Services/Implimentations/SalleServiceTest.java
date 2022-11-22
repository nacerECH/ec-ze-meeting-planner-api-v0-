package com.zenika.meetingplannerapi.Services.Implimentations;


import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.entities.Equipement;
import com.zenika.meetingplannerapi.entities.Salle;
import com.zenika.meetingplannerapi.entities.enumerations.EtatSalle;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalleServiceTest {
    @Autowired
    private SalleService salleService;


    @Test
    public void should_add_one_Salle() {
        List<Equipement> equipements = new ArrayList<>();
        equipements.add(new Equipement("Neant",null));

        Salle expectedSalle =new Salle();
        expectedSalle.setNom("E10001");
        expectedSalle.setEtatSalle(EtatSalle.LIBRE);
        expectedSalle.setCapacite(25);
        expectedSalle.setEquipements(equipements);

        Salle addedSalle = salleService.AddSalle(expectedSalle);


        assertNotNull(addedSalle);
        assertEquals(addedSalle.toString(),expectedSalle.toString());


    }


    @Test
    public void should_get_salle_by_name(){

        List<Equipement> equipements = new ArrayList<>();
        equipements.add(new Equipement("Neant",null));

        Salle expectedSalle =new Salle();
        expectedSalle.setNom("E10001");
        expectedSalle.setEtatSalle(EtatSalle.LIBRE);
        expectedSalle.setCapacite(25);
        salleService.AddSalle(expectedSalle);

        Salle getedSalle = salleService.GetSalleByName(expectedSalle.getNom());

        assertNotNull(getedSalle);
        assertEquals(getedSalle.getNom(),expectedSalle.getNom());
        assertEquals(getedSalle.getEtatSalle(),expectedSalle.getEtatSalle());
        assertEquals(getedSalle.getCapacite(),expectedSalle.getCapacite());
        assertNotNull(getedSalle.getEquipements());

    }

    @Test

    public  void should_ge_liste_des_salles_disponibles(){

        List<Equipement> equipements = new ArrayList<>();
        equipements.add(new Equipement("Ecran",null));
        equipements.add(new Equipement("Tableau",null));

        Salle salleReservee =new Salle();
        salleReservee.setNom("E1007");
        salleReservee.setEtatSalle(EtatSalle.RESERVEE);
        salleReservee.setCapacite(25);
        salleReservee.setEquipements(equipements);
        salleService.AddSalle(salleReservee);

        Salle salleEnNettoyage =new Salle();
        salleEnNettoyage.setNom("E1006");
        salleEnNettoyage.setEtatSalle(EtatSalle.EN_NETTOYAGE);
        salleEnNettoyage.setCapacite(25);
        salleEnNettoyage.setEquipements(equipements);
        salleService.AddSalle(salleEnNettoyage);

        Salle sallelibre =new Salle();
        sallelibre.setNom("E1005");
        sallelibre.setEtatSalle(EtatSalle.LIBRE);
        sallelibre.setCapacite(25);
        sallelibre.setEquipements(equipements);
        salleService.AddSalle(sallelibre);


        assertEquals(salleService.GetSallesDisponibles().size(),13); // deja on 12 element dans la liste (initiliser par Bean in CommandLineRunner)
        assertEquals(salleService.GetSallesDisponibles().get(12).getNom(),sallelibre.getNom());  // 12 l'indice de derniere element ajoutee
        assertEquals(salleService.GetSallesDisponibles().get(12).getEtatSalle(),sallelibre.getEtatSalle());
        assertEquals(salleService.GetSallesDisponibles().get(12).getCapacite(),sallelibre.getCapacite());

    }

    @Test
    public void should_get_listes_des_salles_convenables_pour_une_reinion(){

        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setCreneau(9);
        reunionDto.setNombre_personnes_conviees(5);
        reunionDto.setTypeReunion(TypeReunion.RS);

        List<SalleDto> listSallesConvenables = salleService.GetSallesConvenables(reunionDto);

        assertNotNull(listSallesConvenables);
        assertFalse(listSallesConvenables.size()==4);  // on a quatres salles convenables a cette reinion (ajeutees par CommandLineRunner)

    }


    @Test
    public void test_CheckIfReunionTypeAssureeParSalle(){

        List<Equipement> equipements = new ArrayList<>();
        equipements.add(new Equipement("Ecran",null));
        equipements.add(new Equipement("Tableau",null));

        Salle salle =new Salle();
        salle.setNom("E1007");
        salle.setEtatSalle(EtatSalle.LIBRE);
        salle.setCapacite(25);
        salle.setEquipements(equipements);
        // Cette salles et leurs equipements assurent les reinions : SPEC,RS
        salleService.AddSalle(salle);

        List<String> reunionsAssurees = new ArrayList<>();
        if(salleService.CheckIfReunionTypeAssuree(salle,TypeReunion.SPEC)){reunionsAssurees.add("SPEC");}
        if(salleService.CheckIfReunionTypeAssuree(salle,TypeReunion.RS)){reunionsAssurees.add("RS");}
        if(salleService.CheckIfReunionTypeAssuree(salle,TypeReunion.RC)){reunionsAssurees.add("RC");}
        if(salleService.CheckIfReunionTypeAssuree(salle,TypeReunion.VC)){reunionsAssurees.add("VC");}


        assertTrue(reunionsAssurees.size() ==2 && reunionsAssurees.contains("SPEC") && reunionsAssurees.contains("RS"));

    }


}