package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.domain.Role;
import com.vedegiska.gateway_microservice.exception.EmailAddressUnavailableException;
import com.vedegiska.gateway_microservice.repo.RoleRepository;
import com.vedegiska.gateway_microservice.repo.UserRepository;
import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IAppUserDetailsServiceImpl implements IAppUserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void saveUser(User user, Set<String> roleStr) {
        Optional<com.vedegiska.gateway_microservice.domain.User> isUserEmailAlready =
                userRepository.findByEmail(user.getUsername());
        if (isUserEmailAlready.isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (String role : roleStr) {
                //roles.add
            }
        } else {
            throw new EmailAddressUnavailableException("Error. User with this email already exist");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new EmailAddressUnavailableException("Error. User with this email is not found"));

    }
}
