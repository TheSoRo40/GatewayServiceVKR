package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.OfferVO;
import org.springframework.http.ResponseEntity;

public interface IDeliveryService {
    ResponseEntity<Object>  listDeliveriesByCourierId(Long courierId);
    ResponseEntity<Object> recreateDelivery(OfferVO offer);
}
