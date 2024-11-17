package com.scotiatech.demo.controller;


import com.scotiatech.demo.Service.UsuarioService;
import com.scotiatech.demo.dto.AcessoRequestDto;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.UsuarioRequestDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseDto<UsuarioEntity> registerUser (@Validated @RequestBody UsuarioRequestDto user){
        return usuarioService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseDto<String> login(@RequestBody AcessoRequestDto loginRequest) throws Exception {
        return usuarioService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
