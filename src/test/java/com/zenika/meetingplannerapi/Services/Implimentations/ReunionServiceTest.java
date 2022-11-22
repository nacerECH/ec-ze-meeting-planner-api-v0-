package com.zenika.meetingplannerapi.Services.Implimentations;

import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.entities.Reunion;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import com.zenika.meetingplannerapi.exceptions.SaveReunionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReunionServiceTest {

    @Autowired
    private  ReunionService reunionService;
    @Autowired
    private SalleService salleService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void  Test_get_mailleure_salle() throws SaveReunionException {

        ReunionDto reunionHasMelleureSalle = new ReunionDto();
        reunionHasMelleureSalle.setCreneau(9);
        reunionHasMelleureSalle.setNombre_personnes_conviees(7);
        reunionHasMelleureSalle.setTypeReunion(TypeReunion.RS);

        ReunionDto reunionDoesntHaveMelleureSalle = new ReunionDto();
        reunionDoesntHaveMelleureSalle.setCreneau(9);
        reunionDoesntHaveMelleureSalle.setNombre_personnes_conviees(8000);
        reunionDoesntHaveMelleureSalle.setTypeReunion(TypeReunion.RS);

        //la meilleure salle pour cette reinion E10001
        SalleDto mailleureSalle = reunionService.GetMeilleureSalle(reunionHasMelleureSalle);

        //Ce reinion n'a pas une meilleure salle
        SalleDto mailleureSalle2 = reunionService.GetMeilleureSalle(reunionDoesntHaveMelleureSalle);

        assertEquals(salleService.GetSalleByName(mailleureSalle.getNom()).getNom(),"E10001");
        assertNull(mailleureSalle2);

    }

    @Test
    public void should_save_reunion() throws SaveReunionException {

        ReunionDto expectedReunion = new ReunionDto();
        expectedReunion.setCreneau(9);
        expectedReunion.setNombre_personnes_conviees(7);
        expectedReunion.setTypeReunion(TypeReunion.RS);
        expectedReunion.setSalle_name("E10002");

        Reunion savedReunion = reunionService.SaveReunion(expectedReunion);


        assertNotNull(savedReunion);
        assertEquals(savedReunion.getSalle().getNom(),expectedReunion.getSalle_name());
        assertEquals(savedReunion.getCreneau(),expectedReunion.getCreneau());
        assertEquals(savedReunion.getTypeReunion(),expectedReunion.getTypeReunion());
        assertEquals(savedReunion.getNombre_personnes_conviees(),expectedReunion.getNombre_personnes_conviees());



    }

    @Test
    public void should_save_reunion_throw_exception() throws SaveReunionException {

        ReunionDto expectedReunion = new ReunionDto();
        expectedReunion.setCreneau(9);
        expectedReunion.setNombre_personnes_conviees(55);
        expectedReunion.setTypeReunion(TypeReunion.RS);

        exception.expect(SaveReunionException.class);
        Reunion savedReunion = reunionService.SaveReunion(expectedReunion);


    }



























}