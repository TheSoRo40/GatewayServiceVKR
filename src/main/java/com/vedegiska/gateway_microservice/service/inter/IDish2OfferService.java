package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.Dish2OfferVO;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface IDish2OfferService {
    ResponseEntity<? super Object> getTrash(long offerId);
    ResponseEntity<? super Object> addDishesToCreatingOffer(Set<Dish2OfferVO> trash, long offerId);
}
