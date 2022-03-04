package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface IAppUserDetailsService extends UserDetailsService {
    Long saveUser(User user, Set<String> roleStr);
    User findUser(Long id);
    User findUser(String email);
}
