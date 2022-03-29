package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.OfferVOChangeStatus;
import com.vedegiska.gateway_microservice.dto.OfferVODeliveryStatus;
import com.vedegiska.gateway_microservice.dto.OfferVOMakingStatus;
import org.springframework.http.ResponseEntity;

public interface IOfferService {
    ResponseEntity<Object> changeStatusOnly(OfferVOChangeStatus offerVO);
    ResponseEntity<Object> changeStatusToWaiting(OfferVOMakingStatus offerVO);
    ResponseEntity<Object> changeStatusToComplete(Long offerId);
    ResponseEntity<Object> listOffers();
    ResponseEntity<Object> deliveryStatusOffer(OfferVODeliveryStatus offerVO);
    ResponseEntity<Object> getCurrentStatusOffer(Long offerId);
}
