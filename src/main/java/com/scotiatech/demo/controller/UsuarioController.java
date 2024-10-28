package com.scotiatech.demo.controller;

import com.scotiatech.demo.dto.LoginRequest;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping(path = "/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/Registar")
    public ResponseDto<UsuarioEntity> registerUser(@Validated @RequestBody UsuarioEntity usuario) {
        return usuarioService.registerUser(usuario);
    }

    @PostMapping("/Login")
    public ResponseDto<String> login(@RequestBody @Validated LoginRequest loginRequest) throws Exception {
        return usuarioService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
