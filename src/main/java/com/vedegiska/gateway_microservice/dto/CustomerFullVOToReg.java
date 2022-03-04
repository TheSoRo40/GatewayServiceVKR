package com.vedegiska.gateway_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFullVOToReg {
    private String email;
    private String customer_password;
    private String mobile;
    private String customer_name;
}
