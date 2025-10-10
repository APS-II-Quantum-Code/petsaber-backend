package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quantum_code.petsaber.domain.Especie;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
