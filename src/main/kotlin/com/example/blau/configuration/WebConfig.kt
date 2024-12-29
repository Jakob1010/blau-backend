package com.example.blau.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Allow all paths
            .allowedOriginPatterns(
                "http://localhost:4200",
                "http://159.69.93.197",
                "http://blau-tracking.com",
                "https://blau-tracking.com",
                "https://www.blau-tracking.com"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow necessary methods
            .allowedHeaders("*") // Allow all headers
            .allowCredentials(true) // Allow credentials (cookies, authorization headers, etc.)
    }
}
