package com.zenika.meetingplannerapi.Services.Implimentations;

import com.zenika.meetingplannerapi.entities.Equipement;
import org.junit.Assert;
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
public class EquipementServiceTest {

    @Autowired
    private  EquipementService equipementService;

    @Test
    public void  should_save_list_of_equipement(){

        List<Equipement> expectedList = new ArrayList<>();
        expectedList.add(new Equipement("Ecran",null));
        expectedList.add(new Equipement("Tableau",null));

        List<Equipement> savedList = equipementService.SaveEquipements(expectedList);

        assertNotNull(savedList);
        assertEquals(expectedList.size(),savedList.size());
        assertEquals(expectedList.get(0).getNom(),savedList.get(0).getNom());
        assertEquals(expectedList.get(1).getNom(),savedList.get(1).getNom());
    }



}