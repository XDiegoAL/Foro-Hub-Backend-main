package alura.forohub.api.domain.topicos;

import jakarta.validation.constraints.NotBlank;

public record TopicoDTO(
        @NotBlank
        String titulo,
        String mensaje,
        @NotBlank
        String status,

        Long autorId,

        Long cursoId
) {
}
