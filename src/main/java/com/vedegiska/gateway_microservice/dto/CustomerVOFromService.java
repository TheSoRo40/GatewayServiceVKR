package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVOFromService {
    private Long customer_id;
    private String mobile;
    private Integer customer_card_num;
    private Integer customer_card_point;
    private String customer_name;
}
