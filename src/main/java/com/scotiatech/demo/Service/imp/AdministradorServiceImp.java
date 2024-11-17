package com.scotiatech.demo.Service.imp;

import com.scotiatech.demo.Contantes.ConstanResponses;
import com.scotiatech.demo.Service.AdministradorService;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdministradorServiceImp  implements AdministradorService  {
    private final UsuarioRepository usuarioRepository;


    @Override
    public ResponseDto<List<UsuarioEntity>> getUsers() {
        try {
            List<UsuarioEntity> users = usuarioRepository.findAll();
            return new ResponseDto<>(users, HttpStatus.OK.value(), ConstanResponses.RESPONSE_CORRECT_GET_USERS_ADMIN);
        } catch (Exception e) {
            return new ResponseDto<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), ConstanResponses.RESPONSE_INCORRECT_GET_USERS_ADMIN + e.getMessage());
        }
    }
}
