package com.vedegiska.gateway_microservice.service.inter;

import com.vedegiska.gateway_microservice.dto.DeliveryVO;
import com.vedegiska.gateway_microservice.dto.OfferVO;

import java.util.Set;

public interface IDeliveryService {
    Set<DeliveryVO> listDeliveriesByCourierId(Long courierId);
    OfferVO recreateDelivery(OfferVO offer);
}
