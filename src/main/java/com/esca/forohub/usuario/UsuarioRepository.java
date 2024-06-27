package com.esca.forohub.usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//              findByNombreUsuario
    UserDetails findByNombreUsuario(String nombreUsuario);
}
