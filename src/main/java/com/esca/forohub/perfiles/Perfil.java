package com.esca.forohub.perfiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name= "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String perfil;

    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
        this.perfil = datosRegistroPerfil.perfil();
        //this.id = datosRegistroPerfil.
    }
}
