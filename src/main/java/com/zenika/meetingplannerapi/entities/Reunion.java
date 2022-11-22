package com.zenika.meetingplannerapi.entities;


import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int creneau;
    private TypeReunion typeReunion;
    private int nombre_personnes_conviees;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salle_name")
    private Salle salle;
}
