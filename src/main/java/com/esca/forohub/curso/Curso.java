package com.esca.forohub.curso;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name= "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nombreCurso;
    //private CategoriaCurso categoria;
    private String categoria;


    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombreCurso = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoriaCurso();
    }
}
