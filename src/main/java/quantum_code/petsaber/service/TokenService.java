package quantum_code.petsaber.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import quantum_code.petsaber.config.auth.CustomUserDetails;
import quantum_code.petsaber.domain.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String gerarToken(CustomUserDetails usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("api-petsaber")
                    .withSubject(usuario.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("api-petsaber")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token Invalido ou expirado");
        }
    }
}
