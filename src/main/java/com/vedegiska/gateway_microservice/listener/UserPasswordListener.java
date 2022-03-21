package com.vedegiska.gateway_microservice.listener;

import com.vedegiska.gateway_microservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
@RequiredArgsConstructor
public class UserPasswordListener {
    private final BCryptPasswordEncoder encoder;

    @PrePersist
    @PreUpdate
    public void hashPassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
