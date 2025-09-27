package quantum_code.petsaber.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import quantum_code.petsaber.domain.Usuario;
import quantum_code.petsaber.service.TokenService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);


        String subject = tokenService.getSubject(token);
        Usuario user = null;

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        return authorizationHeader.replace("Bearer ", "");
    }

}
