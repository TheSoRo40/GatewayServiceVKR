package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferVOCreate {
    private OfferVOMakingStatus offer;
    private Set<Dish2OfferVO> trash;
}
