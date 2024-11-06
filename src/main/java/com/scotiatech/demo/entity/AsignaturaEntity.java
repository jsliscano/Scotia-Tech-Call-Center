package com.scotiatech.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Asignatura")
public class AsignaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String programa;

    private Integer grupo;

    private Integer sede;

    @Column(name = "horas_semanales")
    private Integer horasSemanales;

    @Column(name = "horas_semestres")
    private Integer horasSemestres;


}

