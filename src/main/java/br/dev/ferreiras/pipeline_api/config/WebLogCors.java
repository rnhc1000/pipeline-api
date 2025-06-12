package br.dev.ferreiras.pipeline_api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class WebLogCors implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsFilterConfiguration() {

        return new WebMvcConfigurer() {

            /**
             *
             * @param corsRegistry inject dependency to check for authorized sockets
             */

            @Override
            public void addCorsMappings(@NonNull final CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**")
                        .allowedOrigins(
                                "http://192.168.0.18:8000",
                                "http://localhost:8000",
                                "http://127.0.0.1:8000"
                        )
                        .allowedMethods(
                                "GET", "POST", "PUT", "DELETE",
                                "HEAD", "TRACE", "CONNECT")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600L);
            }
        };
    }

}
