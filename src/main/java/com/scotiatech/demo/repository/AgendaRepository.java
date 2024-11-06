package com.scotiatech.demo.repository;

import com.scotiatech.demo.entity.AgendaEntity;
import com.scotiatech.demo.entity.FacultadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

    List<AgendaEntity> findByFacultad(FacultadEntity facultad);
}
