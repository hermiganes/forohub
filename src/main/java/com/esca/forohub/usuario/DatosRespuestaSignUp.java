package com.esca.forohub.usuario;

public record DatosRespuestaSignUp(
        String id,
        String nombre,
        String correo) {
    public DatosRespuestaSignUp (Usuario usuario){
        this(
                usuario.getId().toString(),
                usuario.getNombreUsuario(),
                usuario.getCorreo()
        );
    }
}
