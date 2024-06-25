package com.esca.forohub.topico;

import com.esca.forohub.curso.DatosRegistroCurso;
import com.esca.forohub.usuario.DatosRegistroUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autorId,
        @NotBlank
        String cursoId) {
}
