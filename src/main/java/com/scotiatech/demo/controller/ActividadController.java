package com.scotiatech.demo.controller;


import com.scotiatech.demo.entity.ActividadEntity;
import com.scotiatech.demo.service.ActividadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/api/actividad")

public class ActividadController {

    private final ActividadService actividadService;
    @GetMapping("/All")
    public List<ActividadEntity> getAllActividades() {
        return actividadService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ActividadEntity> getActividadById(@PathVariable Long id) {
        return actividadService.findById(id);
    }

    @GetMapping("/{id}/productos")
    public String[] getProductos(@PathVariable Long id) {
        String[] productosArray = actividadService.getProductos(id);
        return Arrays.stream(productosArray)
                .map(producto -> producto.replace("\r\n", "").replace("\n", "").replace("\r", ""))
                .toArray(String[]::new); // Convertir de nuevo a un String[]
    }

}


