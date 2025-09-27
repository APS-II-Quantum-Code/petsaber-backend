package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quantum_code.petsaber.domain.Consultor;

import java.util.Optional;

public interface ConsultorRepository extends JpaRepository<Consultor, Long> {

    Optional<Consultor> findByEmail(String email);
    boolean existsByEmail(String email);
}
