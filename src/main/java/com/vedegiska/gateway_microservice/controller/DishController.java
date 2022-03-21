package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.service.inter.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {
    private final IDishService dishService;

    @GetMapping
    public ResponseEntity<Object> getDishesMenu() {
        return dishService.getDishesMenu();
    }
}
