package com.esca.forohub.usuario;

import com.esca.forohub.perfiles.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Table(name= "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List <Perfil> perfil;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombreUsuario = datosRegistroUsuario.nombre();
        this.correo = datosRegistroUsuario.correoElectronico();
        this.contrasena = datosRegistroUsuario.contrasena();
    }
}
