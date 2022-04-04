package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.domain.User;
import com.vedegiska.gateway_microservice.dto.*;
import com.vedegiska.gateway_microservice.enums.RoleEnum;
import com.vedegiska.gateway_microservice.repo.UserRepository;
import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import com.vedegiska.gateway_microservice.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final IAppUserDetailsService appUserDetailsService;
    @Value("${name_microservices.workshop}")
    private String baseUrl;

    @Override
    public ResponseEntity<? super Object> createEmployee(EmployeeFullToReg employee) {
        User user = new User(employee.getEmail(), employee.getPassword());
        Set<String> roles = new HashSet<>();
        roles.add(RoleEnum.valueOf(employee.getRole()).toString());
        Long generatedId = appUserDetailsService.saveUser(user, roles);
        HttpEntity<EmployeeVOToRegForWorkShopService> request = new HttpEntity<>(
                new EmployeeVOToRegForWorkShopService(generatedId,
                        employee.getName(), employee.getMobile(),
                        employee.getRestaurantId()));
        return restTemplate.postForEntity(
                ("http://" + baseUrl + "/employee/registration"),
                request,
                Object.class
        );
    }

    @Override
    public ResponseEntity<? super Object> listCouriers(long restaurantId) {
        Set<Long> courierIds = userRepository
                .findByRoles_NameEquals(RoleEnum.ROLE_COURIER.toString())
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        return restTemplate.postForEntity(
                ("http://" + baseUrl + "/employee/all_couriers_in_restaurant"),
                new ListCouriersRequest(restaurantId, courierIds),
                Object.class
        );
    }

    @Override
    public ResponseEntity<? super Object> findById(long id) {
        User user = appUserDetailsService.findUser(id);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_CUSTOMER.toString()))) {
            return restTemplate.getForEntity(
                    ("http://" + baseUrl + "/employee/" + id),
                    Object.class
            );
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }
}
