package quantum_code.petsaber.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quantum_code.petsaber.dto.*;
import quantum_code.petsaber.facade.Facade;

import java.util.List;

@RestController
@RequestMapping("/consultor")
@PreAuthorize("hasRole('CONSULTOR')")
@RequiredArgsConstructor
public class ConsultorController {

    private final Facade facade;

    @GetMapping("/trilhas")
    public List<TrilhaResponseDto> buscarTodasAsTrilhas(){
        return facade.buscarTrilhas();
    }

    @GetMapping("/trilhas/{idTrilha}")
    public TrilhaResponseDto buscarTrilhaPorId(@PathVariable Long idTrilha){
        return facade.buscarTrilhaPorId(idTrilha);
    }

    @PostMapping("/trilhas")
    public void adicionarTrilha(@RequestBody TrilhaRequestDto trilhaRequestDto){
        facade.adicionarTrilha(trilhaRequestDto);
    }

    @PutMapping("/trilhas/{idTrilha}")
    public void editarTrilha(@PathVariable Long idTrilha, @RequestBody TrilhaRequestDto trilhaRequestDto){
        facade.editarTrilha(idTrilha, trilhaRequestDto);
    }

    @DeleteMapping("/trilhas/{idTrilha}")
    public void removerTrilha(@PathVariable Long idTrilha){
        facade.deletarTrilha(idTrilha);
    }

    @GetMapping("/especies")
    public List<EspecieResponseDto> buscarEspecies() {
        return facade.buscarEspecies();
    }

    @GetMapping("/especies/{idEspecie}/racas")
    public List<RacaResponseDto> buscarRacas(@PathVariable Long idEspecie) {
        return facade.buscarRacas(idEspecie);
    }

    @GetMapping("/trilhas/{idTrilha}/modulos")
    public List<ModuloResponseDto> buscarModulosPorIdTrilha(@PathVariable Long idTrilha){
        return facade.buscarModulosPorTrilha(idTrilha);
    }

    @GetMapping("/modulos/{idModulo}")
    public ModuloResponseDto buscarModuloPorId(@PathVariable Long idModulo){
        return facade.buscarModuloPorId(idModulo);
    }

    @GetMapping("/modulos/{idModulo}/exercicios")
    public List<ExercicioResponseDto> buscarExerciciosPorIdModulo(@PathVariable Long idModulo){
        return facade.buscarExerciciosPorIdModulo(idModulo);
    }

    @PostMapping("/trilhas/criar-trilha")
    public TrilhaResponseDto criarNovaTrilha(@RequestBody TrilhaRequestDto trilhaRequestDto){
        return facade.criarNovaTrilha(trilhaRequestDto);
    }

    @PostMapping("/trilhas/{idTrilha}/criar-modulo")
    public ModuloResponseDto criarModulo(@PathVariable Long idTrilha, @RequestBody ModuloRequestDto moduloRequestDto){
        return facade.criarNovoModulo(idTrilha, moduloRequestDto);
    }

    @PostMapping("/modulos/{idModulo}/criar-exercicio")
    public ExercicioResponseDto criarExercicio(@PathVariable Long idModulo, @RequestBody ExercicioRequestDto exercicioRequestDto){
        return facade.criarExercicio(idModulo, exercicioRequestDto);
    }


}
