package com.esca.forohub.controller;

import com.esca.forohub.infra.security.DatosTokenJWT;
import com.esca.forohub.infra.security.TokenService;
import com.esca.forohub.usuario.DatosAutenticarUsuario;
import com.esca.forohub.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticarUsuario datosAutenticarUsuario){
//        System.out.println(datosAutenticarUsuario);
//        System.out.println(datosAutenticarUsuario.usuario());
//        System.out.println(datosAutenticarUsuario.clave());
        Authentication autToken = new UsernamePasswordAuthenticationToken(datosAutenticarUsuario.usuario(),
                datosAutenticarUsuario.clave());
        var usuarioVerificado = authenticationManager.authenticate(autToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioVerificado.getPrincipal());
        return ResponseEntity.ok( new DatosTokenJWT(JWTtoken));
    }
}
