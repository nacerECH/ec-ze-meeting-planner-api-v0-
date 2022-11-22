package com.zenika.meetingplannerapi.mappers;

import com.zenika.meetingplannerapi.Dtos.EquipementDto;
import com.zenika.meetingplannerapi.entities.Equipement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EquipementMapper {

    public EquipementDto FromEquipementToEquipementDto(Equipement Equipement){
        EquipementDto EquipementDto = new EquipementDto();
        BeanUtils.copyProperties(Equipement,EquipementDto);
        return  EquipementDto;
    }

    public Equipement FromEquipementDtoToEquipement(EquipementDto EquipementDto){
        Equipement Equipement = new Equipement();
        BeanUtils.copyProperties(EquipementDto,Equipement);
        return  Equipement;
    }
}
