package tn.esprit.tpfoyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class TpFoyerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TpFoyerApplication.class, args);
    }

    // Configuration de CORS pour autoriser les requêtes HTTP depuis un autre domaine
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Autoriser toutes les requêtes depuis le domaine http://localhost:4200
                registry.addMapping("/**") // Autoriser toutes les requêtes
                        .allowedOrigins("http://localhost:4200") // Autoriser les requêtes depuis ce domaine
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Spécifie les méthodes autorisées
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
