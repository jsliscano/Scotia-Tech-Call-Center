package com.scotiatech.demo.repository;

import com.scotiatech.demo.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByIdentificacion(String identificacion);

    Optional<UsuarioEntity> findByEmail(String email);

    @Query(value = "SELECT r.name FROM usuario_rol ur INNER JOIN rol r ON r.id = ur.rol_id WHERE ur.usuario_id = :usuarioId", nativeQuery = true)
    String getRole(@Param("usuarioId") Long usuarioId);

}
