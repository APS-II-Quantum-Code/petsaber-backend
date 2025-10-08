package quantum_code.petsaber.config.auth;

import lombok.Data;
import org.springframework.stereotype.Component;
import quantum_code.petsaber.enuns.Role;

@Component
@Data
public class AuthContext {

    private Long id;
    private String email;
    private String nome;
    private Role role;

}
