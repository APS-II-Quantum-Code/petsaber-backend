package quantum_code.petsaber.dto;

import lombok.Builder;
import lombok.Data;
import quantum_code.petsaber.enuns.Role;

@Data
@Builder
public class TokenDto {
    private String token;
    private Long idUser;
    private String nome;
    private String email;
    private Role role;
}
