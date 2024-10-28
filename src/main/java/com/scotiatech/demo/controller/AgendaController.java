package com.scotiatech.demo.controller;

import com.scotiatech.demo.entity.AgendaEntity;
import com.scotiatech.demo.entity.FacultadEntity;
import com.scotiatech.demo.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor

@RestController

@RequestMapping("/api/agendas")

public class AgendaController {


   final private AgendaService agendaService;

    @GetMapping("/facultad/{facultadId}")
    public List<AgendaEntity> obtenerAgendasPorFacultad(@PathVariable Long facultadId) {
        FacultadEntity facultad = new FacultadEntity();
        facultad.setId(facultadId); // Asigna el ID de la facultad

        return agendaService.obtenerAgendasPorFacultad(facultad);
    }
}
