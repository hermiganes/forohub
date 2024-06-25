package com.esca.forohub.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaTopico(
        String id,
        String titulo,
        String mensaje,
        String autorId,
        String cursoId) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId().toString(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombreUsuario(),
                //topico.getAutor().getId().toString(),
                //topico.getCurso().getId().toString()
                topico.getCurso().getNombreCurso()
        );
    }
}
