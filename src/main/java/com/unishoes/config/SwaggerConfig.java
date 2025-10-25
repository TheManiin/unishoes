package com.unishoes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI unishoesApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("UniShoes API")
                        .description("API REST da plataforma UniShoes — Produtos, Usuários e Categorias")
                        .version("1.0.0"));
    }
}
