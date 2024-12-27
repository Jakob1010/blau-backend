package com.example.blau.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Allow all paths
            .allowedOrigins("http://localhost:4200") // Replace with your frontend's URL (e.g., Angular's default localhost:4200)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow necessary methods
            .allowedHeaders("*") // Allow all headers
            .allowCredentials(true) // Allow credentials (cookies, authorization headers, etc.)
    }
}
