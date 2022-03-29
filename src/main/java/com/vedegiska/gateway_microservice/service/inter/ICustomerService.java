package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;
import com.vedegiska.gateway_microservice.dto.CustomerVOFull;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    ResponseEntity<Object> customerRegistration(CustomerFullVOToReg customer);
    CustomerVOFull retrieveCustomerData(String email);
    CustomerVOFull retrieveCustomerData(Long id);
    ResponseEntity<Object> changeCardPoints(int cardPoint, String email);
}
