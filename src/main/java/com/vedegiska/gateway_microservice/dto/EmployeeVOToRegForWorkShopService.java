package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVOToRegForWorkShopService {
    private long id;
    private String fio;
    private String mobile;
    private long restaurant;
}
