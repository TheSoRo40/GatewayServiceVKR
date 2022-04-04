package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.service.inter.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DishService implements IDishService {
    private final RestTemplate restTemplate;
    @Value("${name_microservices.dishes}")
    private String baseUrl;

    @Override
    public ResponseEntity<? super Object> getDishesMenu() {
        return restTemplate.getForEntity(
                ("http://" + baseUrl + "/dishes"),
                Object.class
        );
    }
}
