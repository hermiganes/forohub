package com.esca.forohub.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        String autorId,
        @NotBlank
        String cursoId
) {
}
