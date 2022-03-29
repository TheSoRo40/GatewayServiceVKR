package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferVOMakingStatus {
    private long id;
    private int cost;
    private String status;
    private String address;
    private String comment;
}
