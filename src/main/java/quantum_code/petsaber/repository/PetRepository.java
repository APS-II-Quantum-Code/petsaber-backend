package quantum_code.petsaber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantum_code.petsaber.domain.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p " +
            "JOIN FETCH p.tutor " +
            "JOIN FETCH p.raca " +
            "JOIN FETCH p.raca.especie " +
            "JOIN FETCH p.porte " +
            "WHERE p.tutor.idTutor = :idTutor")
    List<Pet> findByTutorId(Long idTutor);
}
