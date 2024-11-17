package com.scotiatech.demo.Service.imp;

import com.scotiatech.demo.Contantes.ConstanResponses;
import com.scotiatech.demo.Service.UsuarioService;
import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.dto.UsuarioRequestDto;
import com.scotiatech.demo.entity.FacultadEntity;
import com.scotiatech.demo.entity.RolEntity;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.repository.FacultadRepository;
import com.scotiatech.demo.repository.ProgramaRepository;
import com.scotiatech.demo.repository.RolRepository;
import com.scotiatech.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository roleRepository;
    private final FacultadRepository facultadRepository;
    private final ProgramaRepository programaRepository;

    @Autowired
    public UsuarioServiceImp(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                             RolRepository roleRepository, FacultadRepository facultadRepository,
                             ProgramaRepository programaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.facultadRepository = facultadRepository;
        this.programaRepository = programaRepository;
    }

    @Override
    public ResponseDto<UsuarioEntity> registerUser(UsuarioRequestDto user) {
        if (usuarioRepository.findByIdentificacion(user.getIdentificacion()).isPresent() || user.getIdentificacion().isEmpty()) {
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.VALIDATE_THE_IDENTIFICATION_NUMBER_IS_ALREADY_REGISTERED);
        }
        if (usuarioRepository.findByEmail(user.getEmail()).isPresent() || user.getEmail().isEmpty()) {
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.EMAIL_ALREADY_REGISTER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UsuarioEntity newUser = new UsuarioEntity();
        newUser.setNombre(user.getNombre());
        newUser.setApellido(user.getApellido());
        newUser.setIdentificacion(user.getIdentificacion());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        Optional<RolEntity> role = roleRepository.findById(user.getRol());
        Optional<FacultadEntity> faculty = facultadRepository.findById(user.getFacultad());

        if (role.isPresent() && faculty.isPresent()) {
            newUser.setRoles(new HashSet<>(Collections.singletonList(role.get())));
            newUser.setFaculty(faculty.get());
            usuarioRepository.save(newUser);
            return new ResponseDto<>(newUser, HttpStatus.CREATED.value(), ConstanResponses.OK);
        } else {
            throw new EntityNotFoundException(ConstanResponses.ONE_OR_MORE_REGISTERS_NOT_FOUND);
        }
    }

    @Override
    public ResponseDto<String> login(String email, String password) {
        UsuarioEntity user = usuarioRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return new ResponseDto<>(null, HttpStatus.NOT_FOUND.value(), ConstanResponses.USER_NOT_FOUND);
        }
        if (user.getRoles().isEmpty()) {
            return new ResponseDto<>(null, HttpStatus.FORBIDDEN.value(), ConstanResponses.VALIDATE_ROL);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), ConstanResponses.INCORRECT_PASSWORD);
        }

        return new ResponseDto<>("Login successful", HttpStatus.OK.value(), ConstanResponses.OK);
    }
}

