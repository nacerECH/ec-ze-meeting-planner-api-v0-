package com.zenika.meetingplannerapi.Services.Interfaces;
import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.entities.Reunion;

import com.zenika.meetingplannerapi.exceptions.SaveReunionException;

import java.util.List;

public interface IReunionService {

    public SalleDto GetMeilleureSalle(ReunionDto reunionDto) throws SaveReunionException;  // Cette fonction pour choisir la meilleure salle parmi les salles convenables en mettant comme critere la capacite des salles
    public Reunion SaveReunion(ReunionDto reunionDto) throws SaveReunionException; // Enregistrer une reunion et La réunion ne peut être enregistrée que si elle a une salle
    public List<ReunionDto> GetReunionsPlanifiees(); // Renvoie une liste des reunions planifiees

}
