package com.scotiatech.demo.entity;

import jakarta.persistence.*;
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
@Table(name = "programa")
public class ProgramaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID del programa

    @Column(nullable = false, unique = true)
    private String nombre; // Nombre del programa

    // Relación muchos a uno con FacultadEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id", nullable = false) // Columna en la tabla programa
    private FacultadEntity facultad; // Facultad a la que pertenece el programa

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsuarioEntity> usuarios = new HashSet<>();
}
