package com.example.demo.config;

import io.swagger.v3.oas.models.OpenApi;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApi openApi() {
        return new OpenApi()
                .info(new Info()
                        .title("Demo API")
                        .version("1.0")
                        .description("API documentation"));
    }
}
