package com.zenika.meetingplannerapi.RestControllers;


import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.Services.Implimentations.ReunionService;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import com.zenika.meetingplannerapi.exceptions.SaveReunionException;
import com.zenika.meetingplannerapi.validators.ReinionValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api("meeting-planner-api")   // annotation de swagger
@Slf4j
@RequestMapping("/meeting-planner-api/")
public class ReunionController {


    private ReunionService reunionService;
    public ReunionController(ReunionService reunionService) {
        this.reunionService = reunionService;
    }




    @PostMapping (path = "reservation",consumes = "application/json")
    @ApiOperation(value = "Trouver une meilleure salle pour une reunion",notes = "S'il existe une meilleure salle, On enregistre la reunion dans la BD, sinon on ne l'enregistre pas")
    ResponseEntity<?> GetMeilleureSalle(@RequestBody ReunionDto reunion) throws SaveReunionException {
        if(ReinionValidator.validate(reunion).isEmpty()){
            SalleDto salleDto = reunionService.GetMeilleureSalle(reunion);
            if(salleDto!=null){
                return new ResponseEntity<SalleDto>(salleDto, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Aucune salle n'est disponible", HttpStatus.OK);
        }
        return  new ResponseEntity<List<String>>(ReinionValidator.validate(reunion), HttpStatus.NOT_ACCEPTABLE);
    }



    @GetMapping("reunions-planifiees")
    @ApiOperation("Renvoie une liste des reunions planifiees")
    ResponseEntity<List<ReunionDto>> GetReunionsPlanifiees(){
        return  new ResponseEntity<List<ReunionDto>>(reunionService.GetReunionsPlanifiees(),HttpStatus.OK);
    }


    @PostMapping("enregistrer-agenda-des-reunions-lundi")
    @ApiOperation("Afficher la reslutat de planning d'agenda des réunions du Lundi matin")
    ResponseEntity<?> Enregistrer() throws SaveReunionException {
        HashMap<String, String> ReinionsSalles = new HashMap<String, String>();
        List<ReunionDto> reunions = new ArrayList<>();

        ReunionDto reunion1 = new ReunionDto();
        reunion1.setId(1L); reunion1.setCreneau(9);reunion1.setTypeReunion(TypeReunion.VC);reunion1.setNombre_personnes_conviees(8);
        reunions.add(reunion1);

        ReunionDto reunion2 = new ReunionDto();
        reunion2.setId(2L);reunion2.setCreneau(9);reunion2.setTypeReunion(TypeReunion.VC);reunion2.setNombre_personnes_conviees(6);
        reunions.add(reunion2);

        ReunionDto reunion3 = new ReunionDto();
        reunion3.setId(3L);reunion3.setCreneau(11);reunion3.setTypeReunion(TypeReunion.RC);reunion3.setNombre_personnes_conviees(4);
        reunions.add(reunion3);

        ReunionDto reunion4 = new ReunionDto();
        reunion4.setId(4L);reunion4.setCreneau(11);reunion4.setTypeReunion(TypeReunion.RS);reunion4.setNombre_personnes_conviees(2);
        reunions.add(reunion4);

        ReunionDto reunion5 = new ReunionDto();
        reunion5.setId(5L);reunion5.setCreneau(11);reunion5.setTypeReunion(TypeReunion.SPEC);reunion5.setNombre_personnes_conviees(9);
        reunions.add(reunion5);

        ReunionDto reunion6 = new ReunionDto();
        reunion6.setId(6L);reunion6.setCreneau(9);reunion6.setTypeReunion(TypeReunion.RC);reunion6.setNombre_personnes_conviees(7);
        reunions.add(reunion6);

        ReunionDto reunion7 = new ReunionDto();
        reunion7.setId(7L);reunion7.setCreneau(8);reunion7.setTypeReunion(TypeReunion.VC);reunion7.setNombre_personnes_conviees(9);
        reunions.add(reunion7);

        ReunionDto reunion8 = new ReunionDto();
        reunion8.setId(8L);reunion8.setCreneau(8);reunion8.setTypeReunion(TypeReunion.SPEC);reunion8.setNombre_personnes_conviees(10);
        reunions.add(reunion8);

        ReunionDto reunion9 = new ReunionDto();
        reunion9.setId(9L);reunion9.setCreneau(9);reunion9.setTypeReunion(TypeReunion.SPEC);reunion9.setNombre_personnes_conviees(5);
        reunions.add(reunion9);

        ReunionDto reunion10 = new ReunionDto();
        reunion10.setId(10L);reunion10.setCreneau(9);reunion10.setTypeReunion(TypeReunion.RS);reunion10.setNombre_personnes_conviees(4);
        reunions.add(reunion10);

        ReunionDto reunion11 = new ReunionDto();
        reunion11.setId(11L);reunion11.setCreneau(9);reunion11.setTypeReunion(TypeReunion.RC);reunion11.setNombre_personnes_conviees(8);
        reunions.add(reunion11);

        ReunionDto reunion12 = new ReunionDto();
        reunion12.setId(12L);reunion12.setCreneau(11);reunion12.setTypeReunion(TypeReunion.VC);reunion12.setNombre_personnes_conviees(12);
        reunions.add(reunion12);

        ReunionDto reunion13 = new ReunionDto();
        reunion13.setId(13L);reunion13.setCreneau(11);reunion13.setTypeReunion(TypeReunion.SPEC);reunion13.setNombre_personnes_conviees(5);
        reunions.add(reunion13);

        ReunionDto reunion14 = new ReunionDto();
        reunion14.setId(14L);reunion14.setCreneau(8);reunion14.setTypeReunion(TypeReunion.VC);reunion14.setNombre_personnes_conviees(3);
        reunions.add(reunion14);

        ReunionDto reunion15 = new ReunionDto();
        reunion15.setId(15L);reunion15.setCreneau(8);reunion15.setTypeReunion(TypeReunion.SPEC);reunion15.setNombre_personnes_conviees(2);
        reunions.add(reunion15);

        ReunionDto reunion16 = new ReunionDto();
        reunion16.setId(16L);reunion16.setCreneau(8);reunion16.setTypeReunion(TypeReunion.VC);reunion16.setNombre_personnes_conviees(12);
        reunions.add(reunion16);

        ReunionDto reunion17 = new ReunionDto();
        reunion17.setId(17L);reunion17.setCreneau(10);reunion17.setTypeReunion(TypeReunion.VC);reunion17.setNombre_personnes_conviees(6);
        reunions.add(reunion17);

        ReunionDto reunion18 = new ReunionDto();
        reunion18.setId(18L);reunion18.setCreneau(11);reunion18.setTypeReunion(TypeReunion.RS);reunion18.setNombre_personnes_conviees(2);
        reunions.add(reunion18);

        ReunionDto reunion19 = new ReunionDto();
        reunion19.setId(19L);reunion19.setCreneau(9);reunion19.setTypeReunion(TypeReunion.RS);reunion19.setNombre_personnes_conviees(4);
        reunions.add(reunion19);

        ReunionDto reunion20 = new ReunionDto();
        reunion20.setId(20L);reunion20.setCreneau(9);reunion20.setTypeReunion(TypeReunion.RC);reunion20.setNombre_personnes_conviees(7);
        reunions.add(reunion20);


        for (ReunionDto r: reunions) {

            SalleDto salleDto = reunionService.GetMeilleureSalle(r);
            if(!(salleDto==null)){ReinionsSalles.put("Reunion : "+ r.getId(),salleDto.getNom());}
            else {ReinionsSalles.put("Reunion : "+r.getId(),"Il n'y a pas de salle convenable");}


        }

        return  new ResponseEntity<HashMap<String,String>>(ReinionsSalles,HttpStatus.OK);


    }







}
