package com.vedegiska.gateway_microservice.filter;

import com.vedegiska.gateway_microservice.service.inter.ITokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends GenericFilterBean {
    private final ITokenAuthenticationService tokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder
                .getContext()
                .setAuthentication(tokenAuthenticationService
                        .getAuthentication((HttpServletRequest) request));
        chain.doFilter(request, response);
    }
}
