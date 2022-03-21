package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.service.inter.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {
    private final RestTemplate restTemplate;
    @Value("${name_microservices.workshop}")
    private final String baseUrl;

    @Override
    public ResponseEntity<? super Object> listRestaurants() {
        return restTemplate.getForEntity(
                (baseUrl + "/restaurant/all"),
                Object.class
        );
    }

    @Override
    public ResponseEntity<? super Object> nearestRestaurantFromAddress(double locationX, double locationY) {
        String urlTemplate = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/restaurants/nearest")
                .queryParam("lat", "{lat}")
                .queryParam("lng", "{lng}")
                .encode()
                .toUriString();
        Map<String, Double> params = new HashMap<>();
        params.put("lat", locationX);
        params.put("lng", locationY);
        return restTemplate.getForEntity(
                urlTemplate,
                Object.class,
                params
        );
    }
}
