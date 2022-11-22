package com.zenika.meetingplannerapi.Services.Interfaces;

import com.zenika.meetingplannerapi.Dtos.ReunionDto;
import com.zenika.meetingplannerapi.Dtos.SalleDto;
import com.zenika.meetingplannerapi.entities.Salle;
import com.zenika.meetingplannerapi.entities.enumerations.TypeReunion;


import java.util.List;

public interface ISalleService {



   public Salle GetSalleByName(String salle_name);
   public List<Salle> GetSallesDisponibles(); // Renvoie une liste des salles disponibles à la réservation
   public List<SalleDto> GetSallesConvenables(ReunionDto reunionDto); // Renvoie une liste de salles adaptées à une certaine réunion
   public boolean CheckIfReunionTypeAssuree(Salle salle, TypeReunion typeReunion); // Vérifier si le type de réunion est assure par les equipements de la salle
   public Salle  AddSalle(Salle salle);  // Pour ajouter une salle
   public void UpdateSallesAvailability();  // Une fonction qui s'exécutera périodiquement pour mettre à jour la disponibilité des salles



}
