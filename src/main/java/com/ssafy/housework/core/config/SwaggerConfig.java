//package com.ssafy.housework.core.config;
//
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//    private static final String TOKEN_TYPE = "JWT";
//    private static final String BEARER_TOKEN_PREFIX = "Bearer";
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI().info(apiInfo()).components(components());
//    }
//
//    private Info apiInfo() {
//        return new Info()
//                .title("Housework REST API")
//                .description("Housework API for SSAFY 12th final project")
//                .version("2.0.0");
//    }
//
//    private Components components() {
//        return new Components();
//    }
//}
