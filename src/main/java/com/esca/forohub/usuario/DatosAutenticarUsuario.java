package com.esca.forohub.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticarUsuario(
        @NotBlank
        String usuario,
        @NotBlank String clave) {
}
