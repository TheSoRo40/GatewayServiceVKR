package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.EmployeeFullToReg;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {
    ResponseEntity<Object> listCouriers(long restaurantId);
    ResponseEntity<Object> findById(long id);
    ResponseEntity<Object> createEmployee(EmployeeFullToReg employee);
}
