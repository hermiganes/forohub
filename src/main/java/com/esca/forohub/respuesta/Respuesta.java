package com.esca.forohub.respuesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name= "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Respuesta {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String mensajeRespuesta;
    private Long topicoId;
    private LocalDateTime fechaRespuesta;
    private Long autorId;
    private Boolean solucion;


    @PrePersist
    protected void onCreate() {
        fechaRespuesta = LocalDateTime.now();
    }


}
