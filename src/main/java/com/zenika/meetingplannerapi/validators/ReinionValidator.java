package com.zenika.meetingplannerapi.validators;

import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ReinionValidator {

    private static List<String>  TypesReinion  = Arrays.asList("RC", "RS", "SPEC","VC");

    public static List<String> validate(ReunionDto reunionDto){

        List<String> errors = new ArrayList<>();
        Calendar rightNow = Calendar.getInstance();
        int hour_rightNow = rightNow.get(Calendar.HOUR_OF_DAY);

        if(reunionDto.getCreneau()<8 || reunionDto.getCreneau() >20  ){
            errors.add("Veuillez entrer l'heure de début de la réunion en format : 24h et entre 08->20");
        }
        if(hour_rightNow - reunionDto.getCreneau() > 0  ){
            errors.add("Veuillez corriger le créneau horaire choisi car ce créneau est déjà passé, l'heure maintenant : "+hour_rightNow);
        }

        if(!(TypesReinion.contains(reunionDto.getTypeReunion().toString())) ){
            errors.add("type de reinion invalide");
        }
        if(reunionDto.getNombre_personnes_conviees()>100){
            errors.add("le nombre des personnes conviees depasse la capacite de nos salles");
        }

        return errors;

    }




}
