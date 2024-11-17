package com.scotiatech.demo.Service;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.UsuarioRequestDto;
import com.scotiatech.demo.entity.UsuarioEntity;

public interface UsuarioService {

        ResponseDto<UsuarioEntity> registerUser(UsuarioRequestDto user);
        ResponseDto<String> login(String email, String password);
}
