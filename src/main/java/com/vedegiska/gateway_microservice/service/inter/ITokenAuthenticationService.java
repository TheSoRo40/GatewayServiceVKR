package com.vedegiska.gateway_microservice.service.inter;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITokenAuthenticationService {
    void addAuthentication(HttpServletResponse res, String username);
    Authentication getAuthentication(HttpServletRequest request);
}
