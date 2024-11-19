package com.scotiatech.demo.security;

import com.scotiatech.demo.entity.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final String SECRET_KEY = "jsliscano12345671007604983jsliscano";  // Cambia por una clave segura

    public String generateToken(UsuarioEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", user.getRoles().stream().findFirst().get().getName());
        claims.put("name", user.getNombre() +" " + user.getApellido());
        claims.put("idUser", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("facultad", user.getFaculty() != null ? user.getFaculty().getNombre() : null);
        claims.put("programa", user.getPrograma() != null ? user.getPrograma().getNombre() : null);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
