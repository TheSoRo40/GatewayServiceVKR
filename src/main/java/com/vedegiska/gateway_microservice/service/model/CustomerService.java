package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.domain.User;
import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;
import com.vedegiska.gateway_microservice.dto.CustomerVOToRegForMainService;
import com.vedegiska.gateway_microservice.enums.RoleEnum;
import com.vedegiska.gateway_microservice.exception.MicroserviceConnectException;
import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import com.vedegiska.gateway_microservice.service.inter.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final RestTemplate restTemplate;
    private final IAppUserDetailsService appUserDetailsService;
    @Value("${main_server.application.base_url}")
    private String baseUrl;

    @Override
    @Transactional
    public Long customerRegistration(CustomerFullVOToReg customer) {
        User user = new User(customer.getEmail(), customer.getCustomer_password());
        Set<String> roles = new HashSet<>();
        roles.add(RoleEnum.ROLE_CUSTOMER.toString());
        Long generatedId = appUserDetailsService.saveUser(user, roles);
        HttpEntity<CustomerVOToRegForMainService> request = new HttpEntity<>(new CustomerVOToRegForMainService(customer.getMobile(), customer.getCustomer_name()));
        ResponseEntity<Long> getId = restTemplate.exchange(
                (baseUrl + "/customers/registration"),
                HttpMethod.POST,
                request,
                Long.class
        );
        if (generatedId.equals(getId.getBody())) {
            return generatedId;
        } else {
            throw new MicroserviceConnectException("Error to registration you");
        }
    }
}
