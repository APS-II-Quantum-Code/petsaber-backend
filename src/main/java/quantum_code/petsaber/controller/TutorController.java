package quantum_code.petsaber.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quantum_code.petsaber.dto.*;
import quantum_code.petsaber.facade.Facade;

import java.util.List;

@RestController
@RequestMapping("/tutor")
@PreAuthorize("hasRole('TUTOR')")
@RequiredArgsConstructor
public class TutorController {

    private final Facade facade;

    @GetMapping("/meu-progresso")
    public ProgressoDto buscarMeuProgresso() {
        return facade.buscarMeuProgresso();
    }

    @GetMapping("/especies")
    public List<EspecieResponseDto> buscarEspecies() {
        return facade.buscarEspecies();
    }

    @GetMapping("/especies/{idEspecie}/racas")
    public List<RacaResponseDto> buscarRacas(@PathVariable Long idEspecie) {
        return facade.buscarRacas(idEspecie);
    }

    @GetMapping("/portes")
    public List<PorteResponseDto> buscarPortes() {
        return facade.buscarPortes();
    }

    @GetMapping("/buscar-pets")
    public List<PetResponseDto> buscarPetsPorTutor() {
        return facade.buscarPetsPorTutor();
    }

    @GetMapping("/buscar-pets/{idPet}")
    public PetResponseDto buscarPetPorId(@PathVariable Long idPet) {
        return facade.buscarPetPorId(idPet);
    }

    @PostMapping("/novo-pet")
    public void adicionarPet(@RequestBody PetRequestDto petDto) {
        facade.adicionarPet(petDto);
    }

    @PutMapping("/editar-pet/{idPet}")
    public void editarPet(@PathVariable Long idPet, @RequestBody PetRequestDto petDto) {
        facade.editarPet(idPet, petDto);
    }

    @DeleteMapping("/deletar-pet/{idPet}")
    public void deletarPet(@PathVariable Long idPet) {
        facade.deletarPet(idPet);
    }

    @GetMapping("/trilhas")
    public Page<TrilhaResponseDto> buscarTodasAsTrilhas(Pageable pageable) {
        return facade.buscarTodasAsTrilhas(pageable);
    }

    //Buscar minhas trilhas
    @GetMapping("/trilhas/minhas-trilhas")
    public Page<ProgressoTrilhaDto> buscarMinhasTrilhas(Pageable pageable) {
        return facade.buscarMinhasTrilhas(pageable);
    }

    @GetMapping("/trilhas/{idTrilha}/meu-progresso")
    public List<ProgressoModuloDto> buscarMeuProgressoPorTrilha(@PathVariable Long idTrilha) {
        return facade.buscarMeuProgressoPorTrilha(idTrilha);
    }

    @PostMapping("/trilhas/{idTrilha}/iniciar")
    public ProgressoTrilhaDto iniciarProgressoTrilha(@PathVariable Long idTrilha) {
        return facade.iniciarProgressoTrilha(idTrilha);
    }

    @GetMapping("/trilhas/{idTrilha}/modulos")
    public List<ModuloResponseDto> buscarModulosPorIdTrilha(@PathVariable Long idTrilha) {
        return facade.buscarModulosPorTrilha(idTrilha);
    }

    @GetMapping("/modulos/{idModulo}")
    public ModuloResponseDto buscarModuloPorId(@PathVariable Long idModulo) {
        return facade.buscarModuloPorId(idModulo);
    }

    @GetMapping("/modulos/{idModulo}/exercicios")
    public List<ExercicioResponseDto> buscarExerciciosPorModuloId(@PathVariable Long idModulo) {
        return facade.buscarExerciciosPorIdModulo(idModulo);
    }

    @PostMapping("/exercicios/{idExercicio}/responder")
    public ProgressoExercicioDto responderExercicio(@PathVariable Long idExercicio, @RequestBody RespostaRequestDto request) {
        return facade.responderExercicio(idExercicio, request);
    }

}
