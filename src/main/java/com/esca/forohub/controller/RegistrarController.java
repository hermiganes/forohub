package com.esca.forohub.controller;

import com.esca.forohub.perfiles.Perfil;
import com.esca.forohub.perfiles.PerfilRepository;
import com.esca.forohub.usuario.DatosRegistroUsuario;
import com.esca.forohub.usuario.DatosRespuestaSignUp;
import com.esca.forohub.usuario.Usuario;
import com.esca.forohub.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/signup")
public class RegistrarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity registrarUsuario (@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                            UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario =  new Usuario(datosRegistroUsuario);
        Perfil perfil = perfilRepository.getReferenceById(1L);
        List<Perfil> perfiles = new ArrayList<>();
        perfiles.add(perfil);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode(datosRegistroUsuario.contrasena());
        usuario.setPerfil(perfiles);
        usuario.setContrasena(result);
        usuarioRepository.save(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaSignUp(usuario));
    }
}
