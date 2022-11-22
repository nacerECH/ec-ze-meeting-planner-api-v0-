package com.zenika.meetingplannerapi.mappers;


import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.entities.Reunion;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class ReunionMapper {

    public ReunionDto FromReunionToReunionDto(Reunion reunion){
        ReunionDto reunionDto = new ReunionDto();
        BeanUtils.copyProperties(reunion,reunionDto);
        reunionDto.setSalle_name(reunion.getSalle().getNom());
        reunionDto.setSalle_capacite(reunion.getSalle().getCapacite());
        return  reunionDto;
    }

    public Reunion FromReunionDtoToReunion(ReunionDto reunionDto){
        Reunion reunion = new Reunion();
        BeanUtils.copyProperties(reunionDto,reunion);
        return  reunion;
    }
}
