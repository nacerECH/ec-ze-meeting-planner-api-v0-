package com.zenika.meetingplannerapi.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @Id
    private String nom;

    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    private List<Salle> salles = new ArrayList<>();

}
