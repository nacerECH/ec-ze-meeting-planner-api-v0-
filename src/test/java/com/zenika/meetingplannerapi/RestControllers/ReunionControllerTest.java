package com.zenika.meetingplannerapi.RestControllers;

import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.Services.Implimentations.ReunionService;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;
import com.zenika.meetingplannerapi.exceptions.SaveReunionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Suite
@ExtendWith(MockitoExtension.class)

public  class ReunionControllerTest {
    @InjectMocks
    ReunionController reunionController;

    @Mock
    ReunionService reunionService ;
    @Test
    public void getMeilleureSalle() throws SaveReunionException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setCreneau(9); reunionDto.setTypeReunion(TypeReunion.RS);reunionDto.setNombre_personnes_conviees(7);

        SalleDto meilleureSalle = new SalleDto();
        meilleureSalle.setNom("E10001");
        meilleureSalle.setCapacite(23);
        when(reunionService.GetMeilleureSalle(any(ReunionDto.class))).thenReturn(meilleureSalle);

        ResponseEntity<?> responseEntity = reunionController.GetMeilleureSalle(reunionDto);

        Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);


    }

    @Test
    public void should_get_list_of_ReunionsPlanifiees() throws SaveReunionException {
        // planifier deux reunions
        ReunionDto reunionDto = new ReunionDto();
        reunionDto.setCreneau(9); reunionDto.setTypeReunion(TypeReunion.RS);reunionDto.setNombre_personnes_conviees(7);
        ReunionDto reunionDto2 = new ReunionDto();
        reunionDto2.setCreneau(10); reunionDto2.setTypeReunion(TypeReunion.RS);reunionDto2.setNombre_personnes_conviees(4);
        List<ReunionDto> reunionDtos=new ArrayList<>();
        reunionDtos.add(reunionDto);
        reunionDtos.add(reunionDto2);

        when(reunionService.GetReunionsPlanifiees()).thenReturn(reunionDtos);

        ResponseEntity<List<ReunionDto>> responseEntity = reunionController.GetReunionsPlanifiees();

        Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
        Assertions.assertEquals(responseEntity.getBody().size(),2);

    }



}