package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.service.inter.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final IRestaurantService restaurantService;

    @GetMapping("/all")
    public ResponseEntity<Object> getRestaurants() {
        return restaurantService.listRestaurants();
    }

    @GetMapping("/nearest")
    public ResponseEntity<Object> idOfNearestRestaurant(@RequestParam("lat") double lat,
                                      @RequestParam("lng") double lng) {
        return restaurantService.nearestRestaurantFromAddress(lat, lng);
    }
}
