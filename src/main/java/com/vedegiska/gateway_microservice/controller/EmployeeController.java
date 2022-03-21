package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    @GetMapping("/all_couriers_in/{restaurant_id}")
    public ResponseEntity<Object> listCouriers(@PathVariable("restaurant_id") long restaurantId) {
        return employeeService.listCouriers(restaurantId);
    }

    @GetMapping("/{courier_id}")
    public ResponseEntity<Object> findById(@PathVariable("courier_id") long courierId) {
        return employeeService.findById(courierId);
    }

}
