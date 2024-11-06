package com.scotiatech.demo.controller;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.service.AdministradorService;
import com.scotiatech.demo.service.imp.AdministradorServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor

@RequestMapping(path = "/api/administrador")
public class AdminstradorController {


        final private AdministradorServiceImp administradorServiceImp;

        @GetMapping("/usuario")
        @PreAuthorize("hasRole('Admin')")
        public ResponseDto<List<UsuarioEntity>> user (){
            return administradorServiceImp.usuario();
        }
    }

