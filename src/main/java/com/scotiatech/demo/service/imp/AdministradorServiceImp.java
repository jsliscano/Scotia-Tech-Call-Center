package com.scotiatech.demo.service.imp;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.repository.UsuarioRepository;
import com.scotiatech.demo.service.AdministradorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdministradorServiceImp implements AdministradorService {

    final private UsuarioRepository usuarioRepository;

    @Override
    public ResponseDto<List<UsuarioEntity>> usuario() {
        try {
            List<UsuarioEntity> userEntityList = usuarioRepository.findAll();

            return new ResponseDto<>(userEntityList, HttpStatus.OK.value(), "Listado de usuarios");

        } catch (Exception e) {
            return new ResponseDto<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al conseguir los usuarios: " + e.getMessage());
        }
    }
}

