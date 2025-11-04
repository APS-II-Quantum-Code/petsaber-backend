package quantum_code.petsaber.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quantum_code.petsaber.config.auth.AuthContext;
import quantum_code.petsaber.dto.PorteResponseDto;
import quantum_code.petsaber.dto.ProgressoExercicioDto;
import quantum_code.petsaber.domain.*;
import quantum_code.petsaber.dto.*;
import quantum_code.petsaber.enuns.Nivel;
import quantum_code.petsaber.mapper.*;
import quantum_code.petsaber.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
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
    private final ProgressoTrilhaService progressoTrilhaService;
    private final ProgressoModuloService progressoModuloService;
    private final ProgressoExercicioService progressoExercicioService;
    private final EspecieService especieService;
    private final RecompensaService recompensaService;

    private final ExericicioMapper exericicioMapper;
    private final AlternativaMapper alternativaMapper;
    private final ModuloMapper moduloMapper;
    private final TrilhaMapper trilhaMapper;
    private final ProgressoTrilhaMapper progressoTrilhaMapper;
    private final ProgressoModuloMapper progressoModuloMapper;
    private final EspecieMapper especieMapper;
    private final RacaMapper racaMapper;
    private final PorteMapper porteMapper;
    private final PetMapper petMapper;
    private final RecompensaMapper recompensaMapper;
    private final TutorMapper tutorMapper;

    private final AuthContext authContext;


    public TokenDto login(LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    public TokenDto register(RegisterRequestDto registerRequestDto) {
        return authService.register(registerRequestDto);
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
        return petMapper.toDto(petService.buscarPetsPorTutorId(idTutor));
    }

    private Integer calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) return 0;
        long anos = ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
        return (int) Math.max(0, anos);
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
        trilha.setHorasTotais(0);
        trilha.setModulosTotais(0);

        return trilhaMapper.toDto(trilhaService.salvarTrilha(trilha));
    }

    @Transactional
    public ModuloResponseDto criarNovoModulo(Long idTrilha, ModuloRequestDto moduloRequestDto) {

        Modulo modulo = moduloMapper.toEntity(moduloRequestDto);

        Trilha trilha = trilhaService.buscarTrilhaPorId(idTrilha);
        trilha.setModulosTotais(trilha.getModulosTotais() + 1);
        trilha.setHorasTotais(trilha.getHorasTotais() + moduloRequestDto.getDuracaoHoras());
        trilhaService.salvarTrilha(trilha);

        modulo.setTrilha(trilha);

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

    @Transactional
    public ProgressoTrilhaDto iniciarProgressoTrilha(Long idTrilha) {

        Long idTutor = authContext.getId();

        Tutor tutor = tutorService.buscarTutorPorId(idTutor);
        Trilha trilha = trilhaService.buscarTrilhaPorId(idTrilha);

        ProgressoTrilha progressoTrilha = progressoTrilhaService.buscarOuCriarTrilha(tutor, trilha);

        //Inicializando os modulos para o usuario
        List<Modulo> modulos = moduloService.buscarModulosPorIdTrilha(idTrilha);
        progressoModuloService.inicializarModulosDaTrilha(progressoTrilha, modulos);

        return progressoTrilhaMapper.toDto(progressoTrilha);
    }

    @Transactional(readOnly = true)
    public Page<ProgressoTrilhaDto> buscarMinhasTrilhas(Pageable pageable) {

        Long idTutor = authContext.getId();
        Page<ProgressoTrilha> progressoTrilha = progressoTrilhaService.buscarProgressoTrilhasPorIdTutor(pageable, idTutor);


        //modulos
        //Modulos Concluidos


        return progressoTrilha.map(progressoTrilhaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ProgressoModuloDto> buscarMeuProgressoPorTrilha(Long idTrilha) {

        Long idTutor = authContext.getId();

        List<ProgressoModulo> progresso = progressoModuloService.buscarProgressoPorIdTutorEIdTrilha(idTutor, idTrilha);

        return progressoModuloMapper.toDto(progresso);
    }

    @Transactional(readOnly = true)
    public ModuloResponseDto buscarModuloPorId(Long idModulo) {
        return moduloMapper.toDto(moduloService.buscarModuloPorId(idModulo));
    }

    @Transactional
    public ProgressoExercicioDto responderExercicio(Long idExercicio, RespostaRequestDto request) {

        Long idTutor = authContext.getId();

        // Buscando exercicio por id
        Exercicio exercicio = exercicioService.buscarExercicioPorId(idExercicio);

        // Buscando o progresso do usuario no modulo da questao
        ProgressoModulo progressoModulo = progressoModuloService.buscarProgressoModuloPorIdTutorEIdModulo(idTutor, exercicio.getModulo().getIdModulo());

        // Busca a alternativa escolhida
        Alternativa alternativa = alternativaService.buscarAlternativaPorId(request.getIdAlternativaEscolhida());

        Boolean correta = alternativa.getCorreta();
        Double pontosObtidos = correta ? exercicio.getPontuacao() : 0.0;

        // Verifica se o exercício já foi respondido corretamente anteriormente
        Optional<ProgressoExercicio> progressoExistente = progressoExercicioService.buscarProgressoExercicio(
                progressoModulo.getIdProgressoModulo(),
                idExercicio
        );

        // Se já foi respondido corretamente, retorna sem salvar novamente
        if (progressoExistente.isPresent()) {
            ProgressoExercicio progresso = progressoExistente.get();
            progresso.setDataTentativa(LocalDateTime.now());
            progresso.setCorreta(correta);
            progresso.setPontosObtidos(pontosObtidos);

            progressoExercicioService.salvarProgressoExercicio(progresso);

            Long exerciciosCorretosCount = progressoExercicioService.contarExerciciosCorretosDoModulo(progressoModulo.getIdProgressoModulo());
            Long totalExercicios = (long) exercicio.getModulo().getExercicios().size();

            progressoModuloService.verificarEAtualizarStatusModulo(progressoModulo, exerciciosCorretosCount, totalExercicios);

            return ProgressoExercicioDto.builder()
                    .correta(correta)
                    .build();
        }


        // Cria e salva o progresso do exercício
        ProgressoExercicio progresso = ProgressoExercicio.builder()
                .exercicio(exercicio)
                .alternativaEscolhida(alternativa)
                .correta(correta)
                .pontosObtidos(pontosObtidos)
                .dataTentativa(LocalDateTime.now())
                .progressoModulo(progressoModulo)
                .build();

        progressoExercicioService.salvarProgressoExercicio(progresso);

        // Atualiza o status do módulo
        Long exerciciosCorretosCount = progressoExercicioService.contarExerciciosCorretosDoModulo(progressoModulo.getIdProgressoModulo());
        Long totalExercicios = (long) exercicio.getModulo().getExercicios().size();

        progressoModuloService.verificarEAtualizarStatusModulo(progressoModulo, exerciciosCorretosCount, totalExercicios);


        // Atualiza o status da trilha
        ProgressoTrilha progressoTrilha = progressoModulo.getProgressoTrilha();
        Integer modulosConcluidos = progressoModuloService.contarModulosConcluidos(progressoTrilha.getIdProgressoTrilha());
        Integer totalModulos = progressoModuloService.contarTotalModulos(progressoTrilha.getIdProgressoTrilha());

        progressoTrilhaService.verificarEAtualizarStatusTrilha(progressoTrilha, modulosConcluidos, totalModulos);


        return ProgressoExercicioDto.builder()
                .correta(correta)
                .build();

    }

    @Transactional(readOnly = true)
    public ProgressoDto buscarMeuProgresso() {

        Long idTutor = authContext.getId();

        return ProgressoDto.builder()
                .qtdPets(petService.contarQtdPetsPorTutor(idTutor))
                .qtdTrilhasConcluidas(progressoTrilhaService.contarQtdTrilhasConcluidas(idTutor))
                .pontosTotais(progressoExercicioService.buscarPontuacao(idTutor))
                .qtdModulosConcluidos(progressoModuloService.contarQtdModulosConcluidas(idTutor))
                .build();
    }

    @Transactional(readOnly = true)
    public List<EspecieResponseDto> buscarEspecies() {
        return especieMapper.toDto(especieService.buscarEspecies());
    }

    @Transactional(readOnly = true)
    public List<RacaResponseDto> buscarRacas(Long idEspecie) {
        return racaMapper.toDto(racaService.buscarRacaPorEspecieId(idEspecie));
    }

    @Transactional(readOnly = true)
    public List<PorteResponseDto> buscarPortes() {
        return porteMapper.toDto(porteService.buscarTodosPortes());
    }

    @Transactional(readOnly = true)
    public PetResponseDto buscarPetPorId(Long idPet) {
        return petMapper.toDto(petService.buscarPetPorId(idPet));
    }

    @Transactional(readOnly = true)
    public Page<TrilhaResponseDto> buscarTodasAsTrilhas(Pageable pageable, Long idRaca, Nivel nivel) {

        Long idTutor = authContext.getId();
        return trilhaService.buscarTrilhas(pageable, idTutor, idRaca, nivel).map(trilhaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public ProgressoModuloDto buscarProgressoModuloPorId(Long idModulo) {

        Long idTutor = authContext.getId();

        return progressoModuloMapper.toDto(progressoModuloService.buscarProgressoModuloPorId(idModulo, idTutor));
    }

    @Transactional(readOnly = true)
    public Page<ItemRankingDto> buscarRanking(Pageable pageable) {

        Long idTutor = authContext.getId();

        return progressoExercicioService.buscarRanking(pageable).map(itemRankingDto -> {
            if (idTutor.equals(itemRankingDto.getIdTutor())) {
                itemRankingDto.setLogado(true);
            }

            return itemRankingDto;
        });
    }

    @Transactional(readOnly = true)
    public Page<RecompensaDto> buscarRecompensas(Pageable pageable) {
        return recompensaService.buscarRecompensas(pageable)
                .map(recompensaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public TutorDto buscarMeusDados() {
        Long idTutor = authContext.getId();
        return tutorMapper.toDto(tutorService.buscarTutorPorId(idTutor));
    }

    @Transactional
    public void atualizarPerfil(TutorDto tutorDto) {
        Long idTutor = authContext.getId();
        tutorService.atualizarPerfil(idTutor, tutorDto);
    }

    @Transactional
    public void atualizarSenha(SenhaTutorDto senhaTutorDto) {
        Long idTutor = authContext.getId();
        tutorService.atualizarSenha(idTutor, senhaTutorDto);
    }
}
