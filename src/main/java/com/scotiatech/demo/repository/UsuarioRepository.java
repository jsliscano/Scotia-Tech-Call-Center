package com.scotiatech.demo.repository;

import com.scotiatech.demo.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> findByIdentificacion(String identificacion);
    Optional<UsuarioEntity> findByEmail(String email);
}
