package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Pet;
import quantum_code.petsaber.repository.PetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public void salvarPet(Pet pet) {
        petRepository.save(pet);
    }


    public List<Pet> buscarPetsPorTutorId(Long idTutor) {
        return petRepository.findByTutorId(idTutor);
    }
}
