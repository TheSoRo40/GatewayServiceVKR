package com.vedegiska.gateway_microservice.service.inter;

import org.springframework.http.ResponseEntity;

public interface IRestaurantService {
    ResponseEntity<Object> listRestaurants();
    ResponseEntity<Object> nearestRestaurantFromAddress(double locationX, double locationY);
}
