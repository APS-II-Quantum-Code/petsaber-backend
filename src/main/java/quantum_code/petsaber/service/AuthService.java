package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.config.auth.CustomUserDetails;
import quantum_code.petsaber.domain.Consultor;
import quantum_code.petsaber.domain.Tutor;
import quantum_code.petsaber.dto.LoginRequestDto;
import quantum_code.petsaber.dto.RegisterRequestDto;
import quantum_code.petsaber.dto.TokenDto;
import quantum_code.petsaber.enuns.Role;
import quantum_code.petsaber.repository.ConsultorRepository;
import quantum_code.petsaber.repository.TutorRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final TutorRepository tutorRepository;
    private final ConsultorRepository consultorRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenDto login(LoginRequestDto loginRequestDto) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getSenha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = tokenService.gerarToken(userDetails);

        return TokenDto.builder()
                .token(token)
                .build();
    }

    public void register(RegisterRequestDto registerRequestDto) {

        if (registerRequestDto.getRole() == Role.TUTOR) {
            Tutor tutor = Tutor.builder()
                    .nome(registerRequestDto.getNome())
                    .email(registerRequestDto.getEmail())
                    .senha(passwordEncoder.encode(registerRequestDto.getSenha()))
                    .cpf(registerRequestDto.getCpf())
                    .build();

            tutorRepository.save(tutor);
        }
        if (registerRequestDto.getRole() == Role.CONSULTOR) {
            Consultor consultor = Consultor.builder()
                    .nome(registerRequestDto.getNome())
                    .email(registerRequestDto.getEmail())
                    .senha(passwordEncoder.encode(registerRequestDto.getSenha()))
                    .cpf(registerRequestDto.getCpf())
                    .build();

            consultorRepository.save(consultor);
        }
    }
}
