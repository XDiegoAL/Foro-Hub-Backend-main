package alura.forohub.api.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByLogin(String nombreUsuario);
    Page<Usuario> findAllByOrderByNombreDesc(Pageable pageable);
}
