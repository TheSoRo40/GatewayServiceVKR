package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.OfferVOChangeStatus;
import com.vedegiska.gateway_microservice.service.inter.IOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
