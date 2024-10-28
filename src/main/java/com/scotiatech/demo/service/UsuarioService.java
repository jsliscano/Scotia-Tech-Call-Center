package com.scotiatech.demo.service;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;

public interface UsuarioService {
    ResponseDto<UsuarioEntity> registerUser(UsuarioEntity usuario);
    ResponseDto<String> login(String email, String password) throws Exception;
}