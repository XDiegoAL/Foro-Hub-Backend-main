package alura.forohub.api.domain.usuario;

public record UsuarioDTO(
        String login,
        String password,
        Long idPerfil
) {
}
