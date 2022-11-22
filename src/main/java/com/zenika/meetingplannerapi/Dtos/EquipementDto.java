package com.zenika.meetingplannerapi.Dtos;


import com.zenika.meetingplannerapi.entities.Salle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipementDto {

    private String nom;
    private List<SalleDto> salleDtos = new ArrayList<>();

}
