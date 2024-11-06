package com.scotiatech.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "El número de identificación es un campo obligatorio")
    @Column(nullable = false, unique = true)
    private String identificacion;

    @NotNull(message = "El nombre es un campo obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "El apellido es un campo obligatorio")
    @Column(nullable = false)
    private String apellido;

    @Email(message = "El email debe ser válido")
    @NotNull(message = "El email es un campo obligatorio")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "La contraseña es un campo obligatorio")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "id_director_programa")
    private Long idDirectorPrograma; // Considera cambiar esto a una relación con otra entidad si es necesario

    // Relación con ProgramaEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_id", nullable = false) // Nombre de la columna en la tabla usuario
    private ProgramaEntity programa;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id") // Asegúrate de que este nombre sea correcto
    )
    private Set<RolEntity> roles = new HashSet<>();
}
