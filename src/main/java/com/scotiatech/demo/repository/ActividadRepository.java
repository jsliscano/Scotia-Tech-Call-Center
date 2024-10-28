package com.scotiatech.demo.repository;

import com.scotiatech.demo.entity.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ActividadRepository extends JpaRepository<ActividadEntity, Long> {
    @Query(nativeQuery = true, value = "select datos from paactividad_producto where id =:id")
    public String[] getprdocutos (@Param("id") Long id);

}
