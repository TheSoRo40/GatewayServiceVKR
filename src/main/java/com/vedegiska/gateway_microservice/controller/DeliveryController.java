package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.DeliveryVO;
import com.vedegiska.gateway_microservice.dto.OfferVO;
import com.vedegiska.gateway_microservice.service.inter.IDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final IDeliveryService deliveryService;

    @GetMapping
    @RequestMapping("/show/{courier_id}")
    public Set<DeliveryVO> showDeliveries(@PathVariable("courier_id") long courierId) {
        return deliveryService.listDeliveriesByCourierId(courierId);
    }

    @PostMapping
    @RequestMapping("/recreate")
    public OfferVO changeDelivery(@RequestBody OfferVO deliveryVORecreate) {
        return deliveryService.recreateDelivery(deliveryVORecreate);
    }
}
