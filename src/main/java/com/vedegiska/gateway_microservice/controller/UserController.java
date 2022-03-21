package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Deprecated(forRemoval = true)
public class UserController {
    private final IAppUserDetailsService userDetailsService;


}
