package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;
import com.vedegiska.gateway_microservice.dto.CustomerVOFull;

public interface ICustomerService {
    Long customerRegistration(CustomerFullVOToReg customer);
    CustomerVOFull retrieveCustomerData(String email);
    CustomerVOFull retrieveCustomerData(Long id);
    Integer changeCardPoints(int cardPoint, String email);
}
