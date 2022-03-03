package com.vedegiska.gateway_microservice.service.inter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface IAppUserDetailsService extends UserDetailsService {
    void saveUser(User user, Set<String> roleStr);
}
