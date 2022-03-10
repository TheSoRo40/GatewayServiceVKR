package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferVO {
    private long id;
    private int cost;
    private String status;
    private String address;
    private String comment;
    private String time;
    private long customer;
    private long employee;
    private Set<String> dishes;
}
