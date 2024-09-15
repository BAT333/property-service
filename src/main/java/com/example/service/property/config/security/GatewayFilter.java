package com.example.service.property.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component

public class GatewayFilter extends OncePerRequestFilter {
    private static final List<String> PUBLIC_URIS = List.of("/public");
    private static final String TRUSTED_PROXY_IP = "172.27.64.1";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if (isPublicUri(requestURI) ||isTrustedForwardedHeader(request)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }
    private boolean isPublicUri(String uri) {
        return PUBLIC_URIS.stream().anyMatch(uri::startsWith);
    }
    private boolean isTrustedForwardedHeader(HttpServletRequest request) {
        String forwardedHeader = request.getHeader("X-Forwarded-For");
        if (forwardedHeader != null) {
            return forwardedHeader.contains(TRUSTED_PROXY_IP);
        }
        return false;
    }
    }

