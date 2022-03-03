package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface IAppUserDetailsService extends UserDetailsService {
    void saveUser(User user, Set<String> roleStr);
}
