package com.zenika.meetingplannerapi.repositories;

import com.zenika.meetingplannerapi.entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {

}
