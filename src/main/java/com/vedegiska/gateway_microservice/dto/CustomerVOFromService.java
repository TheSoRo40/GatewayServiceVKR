package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVOFromService {
    private long customerId;
    private String mobile;
    private long customerCardNum;
    private int customerCardPoint;
    private String customerName;
}
