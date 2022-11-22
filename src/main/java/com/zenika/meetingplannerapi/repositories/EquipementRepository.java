package com.zenika.meetingplannerapi.repositories;


import com.zenika.meetingplannerapi.entities.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, String> {

    Equipement findEquipementByNom(String nom);

}
