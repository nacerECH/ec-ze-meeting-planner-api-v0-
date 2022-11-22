package com.zenika.meetingplannerapi.repositories;


import com.zenika.meetingplannerapi.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, String> {

    @Query("select s from Salle s where  s.etatSalle = com.zenika.meetingplannerapi.entities.enumerations.EtatSalle.LIBRE")
    public List<Salle> GetSallesDisponibles();

    @Query("select s from Salle s where  s.etatSalle <> com.zenika.meetingplannerapi.entities.enumerations.EtatSalle.LIBRE")
    public List<Salle> GetSallesNonDisponibles();

}
