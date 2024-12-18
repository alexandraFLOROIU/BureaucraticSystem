package BeaureaticSystems;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configurează CORS pentru rutele aplicației
        registry.addMapping("/**")  // Permite cereri către toate rutele
                .allowedOrigins("http://localhost:3000", "http://localhost:3001")  // Frontend-ul React pentru client și admin
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permite aceste metode
                .allowedHeaders("*")  // Permite toate headerele
                .allowCredentials(true);  // Permite cookies sau autentificare, dacă este necesar
    }
}
