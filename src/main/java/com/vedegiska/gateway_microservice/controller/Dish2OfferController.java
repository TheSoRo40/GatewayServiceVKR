package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.Dish2OfferVO;
import com.vedegiska.gateway_microservice.dto.OfferVOCreate;
import com.vedegiska.gateway_microservice.service.inter.IDish2OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/dish2offer")
@RequiredArgsConstructor
public class Dish2OfferController {
    private final IDish2OfferService dish2OfferService;

    @GetMapping
    @RequestMapping("/get_trash/{offer_id}")
    public ResponseEntity<Object> showTrash(@PathVariable("offer_id") long offerId) {
        return dish2OfferService.getTrash(offerId);
    }

    @PostMapping
    @RequestMapping("/add_products")
    public ResponseEntity<Void> changeProd(@RequestBody OfferVOCreate offer) {
        dish2OfferService.addDishesToCreatingOffer(offer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
