package quantum_code.petsaber.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import quantum_code.petsaber.enuns.Role;
import quantum_code.petsaber.service.validation.EmailValid;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "O nome deve ser informado")
    private String nome;

    @NotBlank(message = "O cpf deve ser informado")
    private String cpf;

    @NotBlank(message = "O email deve ser informado")
    @EmailValid
    private String email;

    @NotBlank(message = "A senha deve ser informado")
    private String senha;

    @NotNull(message = "A ROLE deve ser informado")
    private Role role;
}
