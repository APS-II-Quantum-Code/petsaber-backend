package quantum_code.petsaber.config.auth;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthContext {

    private String username;
    private Long id;
}
