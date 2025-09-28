package quantum_code.petsaber.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quantum_code.petsaber.dto.LoginRequestDto;
import quantum_code.petsaber.dto.RegisterRequestDto;
import quantum_code.petsaber.facade.Facade;
import quantum_code.petsaber.dto.TokenDto;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class AuthController {

    private final Facade facade;

    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return facade.login(loginRequestDto);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        facade.register(registerRequestDto);
    }
}
