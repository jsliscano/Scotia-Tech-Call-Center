package com.scotiatech.demo.security;

import com.scotiatech.demo.entity.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private final String SECRET_KEY = "jhoanestivenliscano12345671007604983jhoanliscano";  // Cambia por una clave segura

    public String generateToken(UsuarioEntity usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", usuario.getRoles().stream().findFirst().get().getName());
        claims.put("name", usuario.getNombre() +" " + usuario.getApellido());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}

