package quantum_code.petsaber.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "O email deve ser informado")
    private String email;
    @NotBlank(message = "A senha deve ser informado")
    private String senha;

}
