package alura.forohub.api.domain.curso;

import alura.forohub.api.domain.topicos.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "Curso")
@Entity(name = "Curso")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre",unique = true)
    private String nombre;

    @Column(name = "categoria")
    private String categoria;

    @OneToMany(mappedBy = "curso")
    private Set<Topico> topicos;
}
