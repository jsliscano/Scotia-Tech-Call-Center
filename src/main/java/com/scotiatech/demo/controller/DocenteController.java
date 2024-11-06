package com.scotiatech.demo.controller;

import com.scotiatech.demo.entity.DocenteEntity;
import com.scotiatech.demo.service.DocenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    final private DocenteService docenteService;

    @PostMapping("/savedocente")
    public ResponseEntity<DocenteEntity> createDocente(@RequestBody DocenteEntity docente) {
        DocenteEntity savedDocente = docenteService.saveDocente(docente);
        return ResponseEntity.ok(savedDocente);
    }
}


