package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.dto.SenhaTutorDto;
import quantum_code.petsaber.dto.TutorDto;
import quantum_code.petsaber.repository.TutorRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final PasswordEncoder passwordEncoder;

    public Tutor buscarTutorPorId(Long idTutor) {
        return tutorRepository.findById(idTutor).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    public void atualizarPerfil(Long idTutor, TutorDto tutorDto) {
        Tutor tutor = tutorRepository.findById(idTutor).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        tutor.setNome(tutorDto.getNome());
        tutor.setCpf(tutorDto.getCpf());
        tutor.setTelefone(tutorDto.getTelefone());
        tutor.setDataNascimento(tutorDto.getDataNascimento());
        tutor.setGenero(tutorDto.getGenero());
        tutor.setNacionalidade(tutorDto.getNacionalidade());

        tutorRepository.save(tutor);
    }

    public void atualizarSenha(Long idTutor, SenhaTutorDto senhaTutorDto) {

        Tutor tutor = tutorRepository.findById(idTutor).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));


        if (passwordEncoder.matches(senhaTutorDto.getSenhaAtual(), tutor.getSenha())) {
            tutor.setSenha(passwordEncoder.encode(senhaTutorDto.getNovaSenha()));
            tutorRepository.save(tutor);
        } else {
            throw new IllegalArgumentException("Senha Incorreta");
        }
    }
}
