package one.projeto.gof.repository;

import one.projeto.gof.entity.UsuarioInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioInfoRepository extends JpaRepository<UsuarioInfo, Integer> {
    Optional<UsuarioInfo> findByName(String username);

}
