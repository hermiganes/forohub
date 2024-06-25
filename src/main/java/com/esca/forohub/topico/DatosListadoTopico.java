package com.esca.forohub.topico;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        String fecha) {
    public DatosListadoTopico(Topico topico){
        this(   topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor() != null ? topico.getAutor().getNombreUsuario() : null,
                topico.getCurso() != null ? topico.getCurso().getNombreCurso() : null,
                topico.getFecha() != null ? topico.getFecha().toString() : null
        );
    }
}
