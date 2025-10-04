package quantum_code.petsaber.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quantum_code.petsaber.config.auth.AuthContext;
import quantum_code.petsaber.dto.ExercicioResponseDto;
import quantum_code.petsaber.domain.*;
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
    private final TrilhaService trilhaService;
    private final ModuloService moduloService;
    private final ExercicioService exercicioService;
    private final AlternativaService alternativaService;

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

        Trilha trilha = trilhaService.salvarTrilha(Trilha.builder()
                .nome(trilhaRequestDto.getNome())
                .descricao(trilhaRequestDto.getDescricao())
                .nivel(trilhaRequestDto.getNivel())
                .raca(racaService.buscarRacaPorId(trilhaRequestDto.getIdRaca()))
                .build());

        return TrilhaResponseDto.builder()
                .idTrilha(trilha.getIdTrilha())
                .nome(trilha.getNome())
                .descricao(trilha.getDescricao())
                .nivel(trilha.getNivel().name())
                .raca(trilha.getRaca().getNome())
                .build();
    }

    @Transactional
    public ModuloResponseDto criarNovoModulo(Long idTrilha, ModuloRequestDto moduloRequestDto) {

        Modulo modulo = moduloService.salvarModulo(Modulo.builder()
                .nome(moduloRequestDto.getNome())
                .descricao(moduloRequestDto.getDescricao())
                .duracaoHoras(moduloRequestDto.getDuracaoHoras())
                .conteudo(moduloRequestDto.getConteudo())
                .trilha(trilhaService.buscarTrilhaPorId(idTrilha))
                .build());

        return ModuloResponseDto.builder()
                .idModulo(modulo.getIdModulo())
                .nome(modulo.getNome())
                .descricao(modulo.getDescricao())
                .duracaoHoras(modulo.getDuracaoHoras())
                .conteudo(modulo.getConteudo())
                .build();
    }

    @Transactional
    public ExercicioResponseDto criarExercicio(Long idModulo, ExercicioRequestDto exercicioRequestDto) {

        Exercicio exercicio = exercicioService.criarExercicio(Exercicio.builder()
                .nome(exercicioRequestDto.getNome())
                .descricao(exercicioRequestDto.getDescricao())
                .pontuacao(exercicioRequestDto.getPontuacao())
                .modulo(moduloService.buscarModuloPorId(idModulo))
                .build());


        List<AlternativaResponseDto> alternativas = exercicioRequestDto.getAlternativas().stream().map(alternativa -> {
             Alternativa response = alternativaService.salvarAlternativa(Alternativa.builder()
                    .conteudo(alternativa.getConteudo())
                    .correta(alternativa.getCorreta())
                    .exercicio(exercicio)
                    .build());

             return AlternativaResponseDto.builder()
                     .idAlternativa(response.getIdAlternativa())
                     .conteudo(response.getConteudo())
                     .build();
        }).toList();

        return ExercicioResponseDto.builder()
                .idExercicio(exercicio.getIdExercicio())
                .nome(exercicio.getNome())
                .descricao(exercicio.getDescricao())
                .alternativas(alternativas)
                .build();
    }

    @Transactional(readOnly = true)
    public List<TrilhaResponseDto> buscarTrilhas() {
        return trilhaService.buscarTrilhas().stream().map(trilha -> TrilhaResponseDto.builder()
                .idTrilha(trilha.getIdTrilha())
                .nome(trilha.getNome())
                .descricao(trilha.getDescricao())
                .nivel(trilha.getNivel().name())
                .raca(trilha.getRaca().getNome())
                .build()
        ).toList();
    }

    @Transactional(readOnly = true)
    public List<ModuloResponseDto> buscarModulosPorTrilha(Long idTrilha) {
        return moduloService.buscarModulosPorIdTrilha(idTrilha).stream().map(modulo -> ModuloResponseDto.builder()
                .idModulo(modulo.getIdModulo())
                .nome(modulo.getNome())
                .descricao(modulo.getDescricao())
                .duracaoHoras(modulo.getDuracaoHoras())
                .conteudo(modulo.getConteudo())
                .build()).toList();
    }

    @Transactional(readOnly = true)
    public List<ExercicioResponseDto> buscarExerciciosPorIdModulo(Long idModulo) {

        List<Exercicio> exercicio = exercicioService.buscarExerciciosPorIdModulo(idModulo);
    }
}
