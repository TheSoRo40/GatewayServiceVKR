package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFullToReg {
    private String email;
    private String password;
    private String mobile;
    private String name;
    private long restaurantId;
    private String role;
}
