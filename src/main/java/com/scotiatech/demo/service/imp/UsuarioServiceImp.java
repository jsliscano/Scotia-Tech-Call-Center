package com.scotiatech.demo.service.imp;

import com.scotiatech.demo.dto.ResponseDto;
import com.scotiatech.demo.entity.UsuarioEntity;
import com.scotiatech.demo.repository.UsuarioRepository;
import com.scotiatech.demo.security.JwtTokenUtil;
import com.scotiatech.demo.service.UsuarioService; // Asegúrate de importar la interfaz
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImp implements UsuarioService { // Implementar la interfaz

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseDto<UsuarioEntity> registerUser(UsuarioEntity usuario) {
        try {
            if (usuarioRepository.findByIdentificacion(usuario.getIdentificacion()).isPresent() || usuario.getIdentificacion().isEmpty()) {
                return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), "Validar el número de identificación ya está registrado");
            }
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent() || usuario.getEmail().isEmpty()) {
                return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), "El correo ya está registrado, intente con otra opción");
            }

            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);

            return new ResponseDto<>(usuario, HttpStatus.CREATED.value(), "Registrado correctamente");
        } catch (Exception e) {
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), "Ocurrió un problema en el proceso de registro");
        }
    }

    @Override
    public ResponseDto<String> login(String email, String password) throws Exception {
        try {
            UsuarioEntity usuario = usuarioRepository.findByEmail(email).orElse(null);
            if (usuario == null) {
                return new ResponseDto<>(null, HttpStatus.NOT_FOUND.value(), "Usuario no encontrado");
            }

            if (usuario.getRoles().isEmpty()) {
                return new ResponseDto<>(null, HttpStatus.FORBIDDEN.value(), "No se le ha asignado un rol. Contacte al administrador.");
            }

            if (!passwordEncoder.matches(password, usuario.getPassword())) {
                return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), "Contraseña incorrecta");
            }

            String token = jwtTokenUtil.generateToken(usuario);
            return new ResponseDto<>(token, HttpStatus.OK.value(), "Login exitoso");
        } catch (Exception e) {
            return new ResponseDto<>(null, HttpStatus.BAD_REQUEST.value(), "Ocurrió un problema en el proceso de login");
        }
    }
}

