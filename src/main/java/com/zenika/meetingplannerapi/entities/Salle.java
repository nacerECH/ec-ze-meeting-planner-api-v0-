package com.zenika.meetingplannerapi.entities;


import com.zenika.meetingplannerapi.entities.enumerations.EtatSalle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    @Id
    private String nom;
    private int capacite;

    private EtatSalle etatSalle;



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "salles_equipements",
            joinColumns = {
                    @JoinColumn(name = "nomSalle", referencedColumnName = "nom",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "nomEquipement", referencedColumnName = "nom",
                            nullable = false, updatable = false)})
    private List<Equipement> equipements = new ArrayList<>();


    @OneToOne(fetch = FetchType.EAGER,

            mappedBy = "salle")
    private Reunion reunion;






}
