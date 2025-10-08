package quantum_code.petsaber.config.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Value("${cors.allowedMethods}")
    private String allowedMethods;

    @Value("${cors.allowedHeaders}")
    private String allowedHeaders;

    @Value("${cors.corsConfiguration}")
    private String corsConfiguration;

    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;

    @Value("${cors.allowedCredentials:false}")
    private boolean allowedCredentials;

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            public void addCorsMappings(final CorsRegistry registry){
                registry
                    .addMapping(corsConfiguration)
                    .allowedOrigins(allowedOrigins.split(","))
                    .allowedMethods(allowedMethods.split(","))
                    .allowedHeaders(allowedHeaders.split(","))
                    .allowCredentials(allowedCredentials);
            }
        };
    }
}
