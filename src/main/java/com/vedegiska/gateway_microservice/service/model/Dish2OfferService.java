package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.dto.Dish2OfferVO;
import com.vedegiska.gateway_microservice.service.inter.IDish2OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class Dish2OfferService implements IDish2OfferService {
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<? super Object> getTrash(long offerId) {


        return null;
    }

    @Override
    @Deprecated(since = "Убрать отсюда, дожно выполняться в бизне логики утверждения заказа на другом микросервисе")
    public ResponseEntity<? super Object> addDishesToCreatingOffer(Set<Dish2OfferVO> trash, long offerId) {
        return null;
    }
}
