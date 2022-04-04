package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.EmployeeFullToReg;
import com.vedegiska.gateway_microservice.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    @RequestMapping("/registration")
    public ResponseEntity<Object> create(@Validated @RequestBody EmployeeFullToReg employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/all_couriers_in/{restaurant_id}")
    public ResponseEntity<Object> listCouriers(@PathVariable("restaurant_id") long restaurantId) {
        return employeeService.listCouriers(restaurantId);
    }

    @GetMapping("/{courier_id}")
    public ResponseEntity<Object> findById(@PathVariable("courier_id") long courierId) {
        return employeeService.findById(courierId);
    }

}
