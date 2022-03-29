package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.OfferVOCreate;
import org.springframework.http.ResponseEntity;

public interface IDish2OfferService {
    ResponseEntity<Object> getTrash(long offerId);
    ResponseEntity<Void> addDishesToCreatingOffer(OfferVOCreate request);
}
