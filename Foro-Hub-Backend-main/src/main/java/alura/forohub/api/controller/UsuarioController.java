package alura.forohub.api.controller;

import alura.forohub.api.domain.perfiles.Perfil;
import alura.forohub.api.domain.perfiles.PerfilRepository;
import alura.forohub.api.domain.topicos.TopicoDTORespuesta;
import alura.forohub.api.domain.usuario.UserRepository;
import alura.forohub.api.domain.usuario.Usuario;
import alura.forohub.api.domain.usuario.UsuarioDTORespuesta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name="bearer-key")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTORespuesta>> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion){
        var usuarios = userRepository.findAllByOrderByNombreDesc(paginacion).map(
                e -> new UsuarioDTORespuesta(
                        e.getId(),e.getNombre()
                )
        );
        return ResponseEntity.ok(usuarios);
    }
}

