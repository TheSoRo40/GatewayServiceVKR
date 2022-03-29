package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.OfferVOChangeStatus;
import com.vedegiska.gateway_microservice.dto.OfferVODeliveryStatus;
import com.vedegiska.gateway_microservice.dto.OfferVOMakingStatus;
import com.vedegiska.gateway_microservice.service.inter.IOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {
    private final IOfferService offerService;

    @PostMapping
    @RequestMapping("/change_status")
    public ResponseEntity<Object> changeStatus(@RequestBody OfferVOChangeStatus offerVO) {
        return offerService.changeStatusOnly(offerVO);
    }

    @PostMapping
    @RequestMapping("/making_offer")
    public ResponseEntity<Object> makeOffer(@RequestBody OfferVOMakingStatus offerVO) {
        return offerService.changeStatusToWaiting(offerVO);
    }

    @PostMapping
    @RequestMapping("/create_offer/{customer_id}")
    public ResponseEntity<Object> createOffer(@PathVariable("customer_id") long customerId) {
        return offerService.getTrashOffer(customerId);
    }

    @GetMapping
    @RequestMapping("/list_offers")
    public ResponseEntity<Object> list() {
        return offerService.listOffers();
    }

    @PostMapping
    @RequestMapping("/get_delivery")
    public ResponseEntity<Object> deliveryStatusOffer(@RequestBody OfferVODeliveryStatus offerVO) {
        return offerService.deliveryStatusOffer(offerVO);
    }

    @GetMapping
    @RequestMapping("/complete_offer/{offer_id}")
    public ResponseEntity<Object> completeOffer(@PathVariable("offer_id") long offerId) {
        return offerService.changeStatusToComplete(offerId);
    }

    @GetMapping
    @RequestMapping("/get_current_status/{offer_id}")
    public ResponseEntity<Object> getCurrentOfferStatus(@PathVariable("offer_id") long offerId) {
        return offerService.getCurrentStatusOffer(offerId);
    }
}
