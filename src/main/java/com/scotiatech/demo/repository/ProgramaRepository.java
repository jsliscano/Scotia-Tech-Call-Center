package com.scotiatech.demo.repository;

import com.scotiatech.demo.dto.interfazDto.IFacultadDto;
import com.scotiatech.demo.dto.interfazDto.IProgramaDto;
import com.scotiatech.demo.entity.ProgramaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<ProgramaEntity,Long> {

        @Query(value = "SELECT p.program_id AS id, p.name AS name FROM program p WHERE p.faculty_id = :facultyId", nativeQuery = true)
        List<IProgramaDto> findByFacultadId (@Param("facultyId") Long facultadId);

        @Query(value = "SELECT f.faculty_id AS facultyId, f.name AS name FROM faculty f", nativeQuery = true)
        List<IFacultadDto> finAllFacultad ();
}
