package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.domain.User;
import com.vedegiska.gateway_microservice.dto.DeliveryVO;
import com.vedegiska.gateway_microservice.dto.OfferVO;
import com.vedegiska.gateway_microservice.enums.RoleEnum;
import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import com.vedegiska.gateway_microservice.service.inter.IDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeliveryService implements IDeliveryService {
    private final RestTemplate restTemplate;
    private final IAppUserDetailsService appUserDetailsService;
    @Value("${main_server.application.base_url}")
    private String baseUrl;

    @Override
    public ResponseEntity<Object> listDeliveriesByCourierId(Long courierId) {
        User user = appUserDetailsService.findUser(courierId);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_COURIER.toString()))) {
            return restTemplate
                    .exchange(
                            (baseUrl + "/delivery/show/" + courierId),
                            HttpMethod.GET,
                            null,
                            Object.class
                    );
        } else {
            throw new IllegalArgumentException("User with wrong role");
        }
    }

    @Override
    public ResponseEntity<Object> recreateDelivery(OfferVO offer) {
        return restTemplate.postForEntity(
                (baseUrl + "/delivery/recreate"),
                offer,
                Object.class
        );
    }
}
