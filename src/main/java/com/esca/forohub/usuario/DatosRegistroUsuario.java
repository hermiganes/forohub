package com.esca.forohub.usuario;

import com.esca.forohub.perfiles.DatosRegistroPerfil;

public record DatosRegistroUsuario(
        String nombre,
        String correoElectronico,
        String contrasena,
        DatosRegistroPerfil perfil
) {
}
