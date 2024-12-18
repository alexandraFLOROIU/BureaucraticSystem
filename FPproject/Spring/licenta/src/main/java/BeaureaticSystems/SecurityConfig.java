package BeaureaticSystems;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Dezactivează CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Activează CORS
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite accesul pentru toate cererile
                );

        return http.build();
    }

    // Creează o sursă de configurare CORS validă
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000"); // Originea frontend-ului
        corsConfiguration.addAllowedOrigin("http://localhost:3001");
        corsConfiguration.addAllowedMethod("GET"); // Permite metoda GET
        corsConfiguration.addAllowedMethod("POST"); // Permite metoda POST
        corsConfiguration.addAllowedMethod("PATCH"); // Permite metoda PATCH
        corsConfiguration.addAllowedMethod("DELETE"); // Permite metoda DELETE
        corsConfiguration.addAllowedHeader("*"); // Permite toate header-ele
        corsConfiguration.setAllowCredentials(true); // Permite cookie-uri sau autentificare

        // Mapăm configurația pe toate rutele
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    // Adăugăm un bean pentru CorsFilter
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}
