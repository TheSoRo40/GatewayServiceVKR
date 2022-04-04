package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.domain.User;
import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;
import com.vedegiska.gateway_microservice.dto.CustomerVOFromService;
import com.vedegiska.gateway_microservice.dto.CustomerVOFull;
import com.vedegiska.gateway_microservice.dto.CustomerVOToRegForMainService;
import com.vedegiska.gateway_microservice.enums.RoleEnum;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final RestTemplate restTemplate;
    private final IAppUserDetailsService appUserDetailsService;
    @Value("${name_microservices.offer}")
    private String baseUrl;

    @Override
    @Transactional
    public ResponseEntity<? super Object> customerRegistration(CustomerFullVOToReg customer) {
        User user = new User(customer.getEmail(), customer.getCustomerPassword());
        Set<String> roles = new HashSet<>();
        roles.add(RoleEnum.ROLE_CUSTOMER.toString());
        Long generatedId = appUserDetailsService.saveUser(user, roles);
        HttpEntity<CustomerVOToRegForMainService> request = new HttpEntity<>(
                new CustomerVOToRegForMainService(generatedId,
                        customer.getMobile(), customer.getCustomerName()));
        return restTemplate.postForEntity(
                ("http://" + baseUrl + "/customers/registration"),
                request,
                Object.class
        );
    }

    @Override
    public CustomerVOFull retrieveCustomerData(String email) {
        User user = appUserDetailsService.findUser(email);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
            return getDetailAboutCustomer(user.getId(), user.getEmail());
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }

    @Override
    public CustomerVOFull retrieveCustomerData(Long id) {
        User user = appUserDetailsService.findUser(id);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
            return getDetailAboutCustomer(user.getId(), user.getEmail());
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }

    @Override
    public ResponseEntity<? super Object> changeCardPoints(int cardPoint, String email) {
        User user = appUserDetailsService.findUser(email);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
/*
            String urlTemplate = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + "/customers/changePoints")
                    .queryParam("card_point", "{card_point}")
                    .queryParam("customer_id", "{customer_id}")
                    .encode()
                    .toUriString();
            */
            Map<String, ? super Number> params = new HashMap<>();
            params.put("card_point", cardPoint);
            params.put("customer_id", user.getId());
            return restTemplate
                    .exchange(
                            ("http://" + baseUrl + "/customers/changePoints"),
                            HttpMethod.PUT,
                            null,
                            Object.class,
                            params);
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }

    private CustomerVOFull getDetailAboutCustomer(Long id, String email) {
        ResponseEntity<CustomerVOFromService> partOfCustomer = restTemplate
                .getForEntity(
                        ("http://" + baseUrl + "/customers/" + id),
                        CustomerVOFromService.class
                );
        return new CustomerVOFull(partOfCustomer.getBody(), email);
    }
}
