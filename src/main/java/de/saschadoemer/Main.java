package de.saschadoemer;

import de.saschadoemer.kortpress.security.ApiKeyAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
