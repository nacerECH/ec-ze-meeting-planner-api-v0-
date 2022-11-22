package com.zenika.meetingplannerapi.Dtos;

import com.zenika.meetingplannerapi.entities.Salle;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data

public class ReunionDto {

    private Long id;
    @ApiModelProperty(notes = "un entier 8 et 20 represente l'heure de debut de la reunion ",name="id",required=true)
    private int creneau;
    @ApiModelProperty(notes = "il peut prendre qutres valeurs SPEC,RC,RS,VC ",name="typeReunion",required=true)
    private TypeReunion typeReunion;
    @ApiModelProperty(notes = "Ne doit pas deppasser 100 ",name="nombre_personnes_conviees",required=true)
    private int nombre_personnes_conviees;
    private String salle_name;
    private int salle_capacite;
}
