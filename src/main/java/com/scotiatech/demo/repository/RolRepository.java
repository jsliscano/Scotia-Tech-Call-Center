package com.scotiatech.demo.repository;

import com.scotiatech.demo.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

}

