package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.domain.User;
import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;
import com.vedegiska.gateway_microservice.dto.CustomerVOFromService;
import com.vedegiska.gateway_microservice.dto.CustomerVOFull;
import com.vedegiska.gateway_microservice.dto.CustomerVOToRegForMainService;
import com.vedegiska.gateway_microservice.enums.RoleEnum;
import com.vedegiska.gateway_microservice.exception.MicroserviceConnectException;
import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import com.vedegiska.gateway_microservice.service.inter.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @Override
    public CustomerVOFull retrieveCustomerData(String email) {
        User user = appUserDetailsService.findUser(email);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
            ResponseEntity<CustomerVOFromService> partOfCustomer = restTemplate
                    .getForEntity(
                            (baseUrl + "/customers/byemail/" + email),
                            CustomerVOFromService.class
                    );
            return new CustomerVOFull(partOfCustomer.getBody(), email);
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }

    @Override
    public CustomerVOFull retrieveCustomerData(Long id) {
        User user = appUserDetailsService.findUser(id);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
            ResponseEntity<CustomerVOFromService> partOfCustomer = restTemplate
                    .getForEntity(
                            (baseUrl + "/customers/" + id),
                            CustomerVOFromService.class
                    );
            return new CustomerVOFull(partOfCustomer.getBody(), user.getEmail());
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }

    @Override
    public Integer changeCardPoints(Integer cardPoint, String email) {
        User user = appUserDetailsService.findUser(email);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
            return restTemplate
                    .exchange(
                            (baseUrl + "/customers/changePoints/" + cardPoint),
                            HttpMethod.PUT,
                            null,
                            Integer.class).
                    getBody();
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }
}
