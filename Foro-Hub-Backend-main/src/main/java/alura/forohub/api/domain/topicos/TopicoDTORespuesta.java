package alura.forohub.api.domain.topicos;

import alura.forohub.api.domain.curso.Curso;
import alura.forohub.api.domain.usuario.Usuario;
import lombok.ToString;

import java.time.LocalDateTime;

public record TopicoDTORespuesta(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String nombreUsuario,
        String nombreCurso,
        LocalDateTime fechaCreacion
) {
    @Override
    public String toString() {
        return "TopicoDTORespuesta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", status='" + status + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", nombreCurso='" + nombreCurso + '\'' +
                '}';
    }
}
