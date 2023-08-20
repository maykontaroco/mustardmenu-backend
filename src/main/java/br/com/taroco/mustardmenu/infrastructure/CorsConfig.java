package br.com.taroco.mustardmenu.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Substitua pelo domínio do seu aplicativo Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Métodos HTTP permitidos
    }
}
