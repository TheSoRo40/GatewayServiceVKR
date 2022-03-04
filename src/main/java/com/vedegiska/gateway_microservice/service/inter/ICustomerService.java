package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;

public interface ICustomerService {

    Long customerRegistration(CustomerFullVOToReg customer);

}
