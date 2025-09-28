package quantum_code.petsaber.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quantum_code.petsaber.config.auth.AuthContext;
import quantum_code.petsaber.domain.Pet;
import quantum_code.petsaber.domain.Porte;
import quantum_code.petsaber.domain.Raca;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.dto.*;
import quantum_code.petsaber.service.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Facade {

    private final AuthService authService;
    private final PetService petService;
    private final TutorService tutorService;
    private final RacaService racaService;
    private final PorteService porteService;

    private final AuthContext authContext;


    public TokenDto login(LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    public void register(RegisterRequestDto registerRequestDto) {
        authService.register(registerRequestDto);
    }

    @Transactional
    public void adicionarPet(PetRequestDto petDto) {

        Long idTutor = authContext.getId();

        Tutor tutor = tutorService.buscarTutorPorId(idTutor);
        Raca raca = racaService.buscarRacaPorId(petDto.getIdRaca());
        Porte porte = porteService.buscarPortePorId(petDto.getIdPorte());

        petService.salvarPet(Pet.builder()
                .nome(petDto.getNome())
                .dataNascimento(petDto.getDataNascimento())
                .sexo(petDto.getSexo())
                .tutor(tutor)
                .raca(raca)
                .porte(porte)
                .build());

    }

    @Transactional(readOnly = true)
    public List<PetResponseDto> buscarPetsPorTutor() {

        Long idTutor = authContext.getId();

        List<Pet> pets = petService.buscarPetsPorTutorId(idTutor);

        return pets.stream().map(pet -> PetResponseDto.builder()
                        .nome(pet.getNome())
                        .urlImagem(pet.getUrlImagem())
                        .sexo(pet.getNome())
                        .dataNascimento(pet.getDataNascimento())
                        .raca(pet.getRaca().getNome())
                        .especie(pet.getRaca().getEspecie().getNome())
                        .porte(pet.getPorte().getNome())
                        .build())
                .toList();
    }
}
