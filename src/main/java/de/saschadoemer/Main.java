package de.saschadoemer;

import de.saschadoemer.kortpress.security.ApiKeyAuthFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@OpenAPIDefinition(
        info = @Info(
                title = "Kortpress Image Converter",
                version = "1.0",
                description = "This is a simple image converter service that provides simple image manipulation tasks."
        ),
        security = {
                @SecurityRequirement(name = "X-Api-Key")
        }
)
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "X-Api-Key",
        in = SecuritySchemeIn.HEADER)
@EnableWebMvc
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public FilterRegistrationBean<Filter> apiKeyAuthFilterFilterRegistrationBean(ApiKeyAuthFilter apiKeyAuthFilter) {
        var registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(apiKeyAuthFilter);
        registrationBean.addUrlPatterns("/image-converter/*");
        return registrationBean;
    }
}
