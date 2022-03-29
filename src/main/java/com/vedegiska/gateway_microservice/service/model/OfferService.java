package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.dto.OfferVOChangeStatus;
import com.vedegiska.gateway_microservice.dto.OfferVODeliveryStatus;
import com.vedegiska.gateway_microservice.dto.OfferVOMakingStatus;
import com.vedegiska.gateway_microservice.service.inter.IOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OfferService implements IOfferService {
    private final RestTemplate restTemplate;
    @Value("${name_microservices.offer}")
    private String baseUrl;

    @Override
    public ResponseEntity<Object> changeStatusOnly(OfferVOChangeStatus offerVO) {
        return restTemplate.postForEntity(
                (baseUrl + "/offer/change_status"),
                offerVO,
                Object.class
        );
    }

    @Override
    public ResponseEntity<Object> changeStatusToWaiting(OfferVOMakingStatus offerVO) {
        return restTemplate.postForEntity(
                (baseUrl + "/offer/making_offer"),
                offerVO,
                Object.class
        );
    }

    @Override
    public ResponseEntity<Object> changeStatusToComplete(Long offerId) {
        return restTemplate.getForEntity(
                (baseUrl + "/offer/complete_offer/" + offerId),
                Object.class
        );
    }

    @Override
    public ResponseEntity<Object> listOffers() {
        return restTemplate.getForEntity(
                (baseUrl + "/offer/list_offers"),
                Object.class
        );
    }

    @Override
    public ResponseEntity<Object> deliveryStatusOffer(OfferVODeliveryStatus offerVO) {
        return restTemplate.getForEntity(
                (baseUrl + "/offer/list_offers"),
                Object.class
        );
    }

    @Override
    public ResponseEntity<Object> getCurrentStatusOffer(Long offerId) {
        return restTemplate.getForEntity(
                (baseUrl + "/offer/get_current_status/" + offerId),
                Object.class
        );
    }
}
