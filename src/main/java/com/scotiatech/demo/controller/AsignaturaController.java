package com.scotiatech.demo.controller;

import com.scotiatech.demo.entity.AsignaturaEntity;
import com.scotiatech.demo.entity.DocenteEntity;
import com.scotiatech.demo.service.AsignaturaService;
import com.scotiatech.demo.service.DocenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Asignatura")

public class AsignaturaController {

    final private AsignaturaService asignaturaService;

    @PostMapping("/saveAsignatura")
    public ResponseEntity<AsignaturaEntity> createAsignatura(@RequestBody AsignaturaEntity asignatura) {
        AsignaturaEntity savedAsignatura = asignaturaService.saveAsignatura(asignatura);
        return ResponseEntity.ok(savedAsignatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaEntity> updateAsignatura(@PathVariable Long id, @RequestBody AsignaturaEntity asignaturaDetails) {
        AsignaturaEntity updatedAsignatura = asignaturaService.update(id, asignaturaDetails);
        return ResponseEntity.ok(updatedAsignatura);
    }
}

