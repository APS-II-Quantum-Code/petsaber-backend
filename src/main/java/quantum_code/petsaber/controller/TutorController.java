package quantum_code.petsaber.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quantum_code.petsaber.dto.PetRequestDto;
import quantum_code.petsaber.dto.PetResponseDto;
import quantum_code.petsaber.facade.Facade;

import java.util.List;

@RestController
@RequestMapping("/tutor")
@PreAuthorize("hasRole('TUTOR')")
@RequiredArgsConstructor
public class TutorController {

    private final Facade facade;

    @GetMapping("/buscar-pets")
    public List<PetResponseDto> buscarPetsPorTutor(){
            return facade.buscarPetsPorTutor();
    }

    @PostMapping("/novo-pet")
    public void adicionarPet(@RequestBody PetRequestDto petDto){
        facade.adicionarPet(petDto);
    }

    @PutMapping("/editar-pet/{idPet}")
    public void editarPet(@PathVariable Long idPet, @RequestBody PetRequestDto petDto){
        facade.editarPet(idPet, petDto);
    }

    @DeleteMapping("/deletar-pet/{idPet}")
    public void deletarPet(@PathVariable Long idPet){
        facade.deletarPet(idPet);
    }
}
