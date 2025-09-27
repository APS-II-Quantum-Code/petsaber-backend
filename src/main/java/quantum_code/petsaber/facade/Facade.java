package quantum_code.petsaber.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quantum_code.petsaber.dto.LoginRequestDto;
import quantum_code.petsaber.dto.RegisterRequestDto;
import quantum_code.petsaber.service.AuthService;
import quantum_code.petsaber.service.TokenDto;
import quantum_code.petsaber.service.TokenService;

@Component
@RequiredArgsConstructor
public class Facade {

    private final AuthService authService;

    public TokenDto login(LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    public void register(RegisterRequestDto registerRequestDto) {
         authService.register(registerRequestDto);
    }
}
