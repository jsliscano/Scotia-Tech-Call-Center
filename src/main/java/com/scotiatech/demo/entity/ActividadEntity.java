package com.scotiatech.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "paactividad_producto")
public class ActividadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "datos", columnDefinition = "TEXT")
    private String datos;  // Asumimos que se guardará como texto, si es JSON también puedes usar `@Type` para especificar el tipo.
}

