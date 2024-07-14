package alura.forohub.api.controller;

import alura.forohub.api.domain.curso.Curso;
import alura.forohub.api.domain.curso.CursoRepository;
import alura.forohub.api.domain.topicos.*;
import alura.forohub.api.domain.usuario.UserRepository;
import alura.forohub.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name="bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<TopicoDTORespuesta> crearTopico(@RequestBody @Valid TopicoDTO topicoDTO, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = userRepository.findById(topicoDTO.autorId()).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(topicoDTO.cursoId()).orElseThrow(()-> new RuntimeException("Curso no encontrado"));
        Topico topico = new Topico(topicoDTO);
        topico.setAutor(usuario);
        topico.setCurso(curso);
        topicoRepository.save(topico);

        TopicoDTORespuesta topicoDTORespuesta = new TopicoDTORespuesta(
                topicoDTO.autorId(), topicoDTO.titulo(), topicoDTO.mensaje(),topicoDTO.status(),usuario.getNombre(),curso.getNombre(),topico.getFechaCreacion()
        );

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        System.out.println(url);
        return ResponseEntity.created(url).body(topicoDTORespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDTORespuesta>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion){
        var topicos = topicoRepository.findAllByOrderByFechaCreacionAsc(paginacion).map(e -> new TopicoDTORespuesta(
                e.getId(), e.getTitulo(), e.getMensaje(),e.getStatus(),e.getAutor().getNombre(),e.getCurso().getNombre(),e.getFechaCreacion()
        ));
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/findbynombrecurso")
    public ResponseEntity<Page<TopicoDTORespuesta>> listadoTopicosPorNombreCurso(@RequestParam String nombreCurso ,@PageableDefault(size = 10) Pageable paginacion){
        nombreCurso.toLowerCase();
        Page<Topico> topicos = topicoRepository.findByCursoNombre(nombreCurso, paginacion);
        Page<TopicoDTORespuesta> datosDTO = topicos.map(e -> new TopicoDTORespuesta(
                e.getId(), e.getTitulo(), e.getMensaje(),e.getStatus(),e.getAutor().getNombre(),e.getCurso().getNombre(),e.getFechaCreacion()
        ));
        return ResponseEntity.ok(datosDTO);
    }

    @GetMapping("/findbyyear")
    public ResponseEntity<Page<TopicoDTORespuesta>> listadoTopicosPorAnio(@RequestParam int year, @PageableDefault(size=10) Pageable paginacion){
        Page <Topico> topicosPorAnio = topicoRepository.findByYear(year,paginacion);
        Page<TopicoDTORespuesta> datosDTO = topicosPorAnio.map(e -> new TopicoDTORespuesta(
                e.getId(), e.getTitulo(), e.getMensaje(),e.getStatus(),e.getAutor().getNombre(),e.getCurso().getNombre(),e.getFechaCreacion()
        ));

        return ResponseEntity.ok(datosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTORespuesta> topicoPorId(@PathVariable Long id) {
        Topico topicoId = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        TopicoDTORespuesta topicoDTORespuesta = new TopicoDTORespuesta(
                topicoId.getId(), topicoId.getTitulo(), topicoId.getMensaje(), topicoId.getStatus(), topicoId.getAutor().getNombre(), topicoId.getCurso().getNombre(), topicoId.getFechaCreacion()
        );
        return ResponseEntity.ok(topicoDTORespuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTORespuesta> actualizaTopico(@PathVariable Long id, @RequestBody TopicoDTO topicoDTO) {
        Optional<Topico> topicoId = topicoRepository.findById(id);
        if(topicoId.isPresent()){
            Optional<Curso> curso = cursoRepository.findById(topicoDTO.cursoId());
            Optional<Usuario> usuario = userRepository.findById(topicoDTO.autorId());
            var topicoObtenido = topicoId.get();
            topicoObtenido.actualizaTopico(topicoDTO);
            if(curso.isPresent()){
                topicoObtenido.setCurso(curso.get());
            }
            if(usuario.isPresent()){
                topicoObtenido.setAutor(usuario.get());
            }
            TopicoDTORespuesta topicoDTORespuesta = new TopicoDTORespuesta(
                    topicoObtenido.getId(), topicoObtenido.getTitulo(), topicoObtenido.getMensaje(), topicoObtenido.getStatus(), topicoObtenido.getAutor().getNombre(), topicoObtenido.getCurso().getNombre(), topicoObtenido.getFechaCreacion()
            );
            topicoRepository.save(topicoObtenido);
            return ResponseEntity.ok(topicoDTORespuesta);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }






}
