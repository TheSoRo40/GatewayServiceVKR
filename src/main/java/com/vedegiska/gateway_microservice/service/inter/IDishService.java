package com.vedegiska.gateway_microservice.service.inter;

import org.springframework.http.ResponseEntity;

public interface IDishService {
    ResponseEntity<Object> getDishesMenu();
}
