package com.scotiatech.demo.controller;

import com.scotiatech.demo.Service.AdministradorService;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService adminitradorService;



    @GetMapping("/users")
    @PreAuthorize("hasRole('Admin')")
    @Operation(
            summary = "Obtiene todos los usuarios",
            description = "Este endpoint devuelve una lista de todos los usuarios registrados en el sistema. Solo accesible para administradores."
    )
    public ResponseDto<List<UsuarioEntity>> getUsers() {
        return adminitradorService.getUsers();
    }
}