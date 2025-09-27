package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.domain.Consultor;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.domain.Usuario;
import quantum_code.petsaber.repository.ConsultorRepository;
import quantum_code.petsaber.repository.TutorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final TutorRepository tutorRepository;
    private final ConsultorRepository consultorRepository;

    public Optional<Usuario> findByEmail(String email) {

        // Primeiro busca nos tutores
        Optional<Tutor> tutor = tutorRepository.findByEmail(email);

        if(tutor.isPresent()){
            return Optional.of(tutor.get());
        }

        //Caso nao encontre, busca nos consultores
        Optional<Consultor> consultor = consultorRepository.findByEmail(email);
        return consultor.map(c -> c);
    }
}
