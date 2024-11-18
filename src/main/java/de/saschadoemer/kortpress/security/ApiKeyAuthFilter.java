package de.saschadoemer.kortpress.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter implements Filter {

    private static final String API_KEY_HEADER = "X-API-KEY";

    @Value("${app.api-key}")
    private String apiKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        var apiKey = httpRequest.getHeader(API_KEY_HEADER);

        if (apiKey == null || !apiKey.equals(this.apiKey)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized, please provide a valid API key.");
        }else{
            chain.doFilter(request, response);
        }
    }
}
