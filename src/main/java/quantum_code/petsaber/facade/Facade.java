package quantum_code.petsaber.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quantum_code.petsaber.config.auth.AuthContext;
import quantum_code.petsaber.domain.*;
import quantum_code.petsaber.dto.*;
import quantum_code.petsaber.mapper.AlternativaMapper;
import quantum_code.petsaber.mapper.ExericicioMapper;
import quantum_code.petsaber.mapper.ModuloMapper;
import quantum_code.petsaber.mapper.TrilhaMapper;
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
    private final TrilhaService trilhaService;
    private final ModuloService moduloService;
    private final ExercicioService exercicioService;
    private final AlternativaService alternativaService;

    private final ExericicioMapper exericicioMapper;
    private final AlternativaMapper alternativaMapper;
    private final ModuloMapper moduloMapper;
    private final TrilhaMapper trilhaMapper;

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
                        .sexo(pet.getSexo())
                        .dataNascimento(pet.getDataNascimento())
                        .raca(pet.getRaca().getNome())
                        .especie(pet.getRaca().getEspecie().getNome())
                        .porte(pet.getPorte().getNome())
                        .build())
                .toList();
    }

    @Transactional
    public void editarPet(Long idPet, PetRequestDto petDto) {

        Pet pet = petService.buscarPetPorId(idPet);

        pet.setNome(petDto.getNome());
        pet.setDataNascimento(petDto.getDataNascimento());
        pet.setSexo(petDto.getSexo());
        pet.setRaca(racaService.buscarRacaPorId(petDto.getIdRaca()));
        pet.setPorte(porteService.buscarPortePorId(petDto.getIdPorte()));

        petService.salvarPet(pet);

    }

    public void deletarPet(Long idPet) {
        petService.deletarPet(idPet);
    }

    @Transactional
    public TrilhaResponseDto criarNovaTrilha(TrilhaRequestDto trilhaRequestDto) {

        Trilha trilha = trilhaMapper.toEntity(trilhaRequestDto);
        trilha.setRaca(racaService.buscarRacaPorId(trilhaRequestDto.getIdRaca()));

        return trilhaMapper.toDto(trilhaService.salvarTrilha(trilha));
    }

    @Transactional
    public ModuloResponseDto criarNovoModulo(Long idTrilha, ModuloRequestDto moduloRequestDto) {

        Modulo modulo = moduloMapper.toEntity(moduloRequestDto);
        modulo.setTrilha(trilhaService.buscarTrilhaPorId(idTrilha));

        return moduloMapper.toDto(moduloService.salvarModulo(modulo));
    }

    @Transactional
    public ExercicioResponseDto criarExercicio(Long idModulo, ExercicioRequestDto exercicioRequestDto) {

        Exercicio exercicio = exericicioMapper.toEntity(exercicioRequestDto);
        exercicio.setModulo(moduloService.buscarModuloPorId(idModulo));
        exercicioService.criarExercicio(exercicio);

        exercicio.getAlternativas().forEach(alternativa -> {
            alternativa.setExercicio(exercicio);
            alternativaService.salvarAlternativa(alternativa);
        });

        return exericicioMapper.toDto(exercicio);
    }

    @Transactional(readOnly = true)
    public List<TrilhaResponseDto> buscarTrilhas() {
        return trilhaMapper.toDto(trilhaService.buscarTrilhas());
    }

    @Transactional(readOnly = true)
    public List<ModuloResponseDto> buscarModulosPorTrilha(Long idTrilha) {
        return moduloMapper.toDto(moduloService.buscarModulosPorIdTrilha(idTrilha));
    }

    @Transactional(readOnly = true)
    public List<ExercicioResponseDto> buscarExerciciosPorIdModulo(Long idModulo) {
        return exericicioMapper.toDto(exercicioService.buscarExerciciosPorIdModulo(idModulo));
    }
}
