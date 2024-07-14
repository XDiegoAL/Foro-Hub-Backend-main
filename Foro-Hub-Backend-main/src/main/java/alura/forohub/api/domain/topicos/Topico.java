package alura.forohub.api.domain.topicos;

import alura.forohub.api.domain.curso.Curso;
import alura.forohub.api.domain.respuesta.Respuesta;
import alura.forohub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "Topico")
@Entity(name="Topico")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    public Topico(TopicoDTO topicoDTO){
        this.titulo = topicoDTO.titulo();
        this.mensaje = topicoDTO.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = topicoDTO.status();
    }

    public int obtenerAnio(){
        return this.fechaCreacion.getYear();
    }

    public void actualizaTopico(TopicoDTO topicoDTO){
        if (topicoDTO.titulo() != null){
            this.titulo = topicoDTO.titulo();
        }
        if(topicoDTO.mensaje() != null){
            this.mensaje = topicoDTO.mensaje();
        }
        if(topicoDTO.status() != null){
            this.status = topicoDTO.status();
        }
    }
}


