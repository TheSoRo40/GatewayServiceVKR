package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferVODeliveryStatus {
    private long offerId;
    private long courierId;
    private String time;
}
