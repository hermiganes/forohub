package com.esca.forohub.controller;

import com.esca.forohub.curso.Curso;
import com.esca.forohub.curso.CursoRepository;
import com.esca.forohub.topico.*;
import com.esca.forohub.usuario.Usuario;
import com.esca.forohub.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity registarTopico(@RequestBody @Valid DatosRegistroTopico parametro,
                                         UriComponentsBuilder uriComponentsBuilder){
        Long autorId = Long.parseLong(parametro.autorId());
        Long cursoId = Long.parseLong(parametro.cursoId());
        Optional<Usuario> autorBase = usuarioRepository.findById(autorId);
        Optional<Curso> cursoBase = cursoRepository.findById(cursoId);
        if (autorBase.isPresent() && cursoBase.isPresent()){
            Topico topico = new Topico(parametro);
            topico.setAutor(autorBase.get());
            topico.setCurso(cursoBase.get());
            topicoRepository.save(topico);
            URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico));
        } else {
            System.out.println("El usuario o el curso no existe");
            return ResponseEntity.notFound().build();
        }
    }//Final registrar Topico


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fecha") Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    } // Listar todos los topicos


    @GetMapping("/{id}")
    public ResponseEntity mostrarMedico(@PathVariable @Valid Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    } // mostrar topico por id


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable @Valid Long id,
                                           @RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(id);
        Long cursoId = Long.parseLong(datosActualizarTopico.cursoId());
        Long autorId = Long.parseLong(datosActualizarTopico.autorId());
        Usuario autorBase = usuarioRepository.getReferenceById(autorId);
        Curso cursoBase = cursoRepository.getReferenceById(cursoId);
        if (datosActualizarTopico.autorId() != null) {
            topico.actulizarTopico(datosActualizarTopico);
            topico.setAutor(autorBase);
            topico.setCurso(cursoBase);

        } else  {
            topico.actulizarTopico(datosActualizarTopico);
            topico.setCurso(cursoBase);
        }
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopicoDb (@PathVariable @Valid Long id,
                                          @RequestParam(name = "borrar", defaultValue = "false") @Valid boolean borrar){
        if (borrar){
            topicoRepository.deleteById(id);
        } else {
            Topico topico = topicoRepository.getReferenceById(id);
            topico.desactivarTopico();
        }
        return ResponseEntity.noContent().build();
    }

}
