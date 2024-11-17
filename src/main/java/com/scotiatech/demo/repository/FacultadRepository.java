package com.scotiatech.demo.repository;

import com.scotiatech.demo.entity.FacultadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<FacultadEntity, Long> {

}

