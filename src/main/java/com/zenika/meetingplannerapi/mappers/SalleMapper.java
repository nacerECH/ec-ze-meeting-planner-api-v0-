package com.zenika.meetingplannerapi.mappers;

import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.entities.Salle;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class SalleMapper {

    EquipementMapper equipementMapper;

    public SalleMapper(EquipementMapper equipementMapper) {
        this.equipementMapper = equipementMapper;
    }

    public SalleDto FromSalleToSalleDto(Salle salle){
        SalleDto salleDto = new SalleDto();
        BeanUtils.copyProperties(salle,salleDto);
        return  salleDto;
    }

    public Salle FromSalleDtoToSalle(SalleDto salleDto){
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleDto,salle);
        return  salle;
    }
}
