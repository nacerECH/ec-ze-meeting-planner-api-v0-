package com.zenika.meetingplannerapi.Dtos;

import com.zenika.meetingplannerapi.entities.Equipement;
import com.zenika.meetingplannerapi.entities.Reunion;
import com.zenika.meetingplannerapi.entities.enumerations.EtatSalle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleDto {

    private String nom;
    private int capacite;
    //private Long reunionId;

}
