package quantum_code.petsaber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.config.auth.AuthContext;
import quantum_code.petsaber.config.auth.CustomUserDetails;
import quantum_code.petsaber.domain.Usuario;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;
    private final AuthContext authContext;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        authContext.setId(usuario.getId());
        authContext.setEmail(usuario.getEmail());
        authContext.setRole(usuario.getRole());
        authContext.setNome(usuario.getNome());

        return new CustomUserDetails(usuario);
    }
}
