package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.dto.OfferVOCreate;
import com.vedegiska.gateway_microservice.service.inter.IDish2OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class Dish2OfferService implements IDish2OfferService {
    private final RestTemplate restTemplate;
    @Value("${name_microservices.dishes}")
    private String baseUrlDishes;

    @Override
    public ResponseEntity<? super Object> getTrash(long offerId) {
        return restTemplate.getForEntity(
                ("http://" + baseUrlDishes + "/dish2offer/get_trash/" + offerId),
                Object.class
        );
    }

    @Override
    public ResponseEntity<Void> addDishesToCreatingOffer(OfferVOCreate request) {
        return restTemplate.postForEntity(
                ("http://" + baseUrlDishes + "/dish2offer/add_products"),
                request,
                Void.class
        );
    }

    /*private Callable<ResponseEntity<Object>> refreshOfferStatus(
            OfferVOMakingStatus offer) {
        return () -> restTemplate.postForEntity(
                (baseUrlOffer + "/making_offer"),
                offer,
                Object.class
        );
    }

    private Callable<ResponseEntity<Object>> addDishes(
            Set<Dish2OfferVO> trash, long offerId) {
        return () -> restTemplate.postForEntity(
                (baseUrlDishes + "/add_products/" + offerId),
                trash,
                Object.class
        );
    }

    ExecutorService service = Executors.newFixedThreadPool(2);
        Future<ResponseEntity<Object>> refreshOffer = service.submit(refreshOfferStatus(request.getOffer()));
        Future<ResponseEntity<Object>> addDishes = service.submit(addDishes(request.getTrash(), request.getOffer().getId()));

        if (refreshOffer.get().getStatusCodeValue() == addDishes.get().getStatusCodeValue() == HttpStatus.OK.value()) {
            re
        }


    */
}
