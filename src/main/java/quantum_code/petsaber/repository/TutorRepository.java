package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quantum_code.petsaber.domain.Tutor;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor,Long> {

    Optional<Tutor> findByEmail(String email);
    boolean existsByEmail(String email);
}
