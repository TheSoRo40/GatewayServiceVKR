package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.CustomerFullVOToReg;
import com.vedegiska.gateway_microservice.dto.CustomerVOFull;
import com.vedegiska.gateway_microservice.service.inter.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping
    @RequestMapping("/registration")
    public Long create(@Validated @RequestBody CustomerFullVOToReg customerDetails) {
        return customerService.customerRegistration(customerDetails);
    }

    @PutMapping
    @RequestMapping("/changePoints/{card_point}")
    public Integer changePoints(
            @PathVariable("card_point") Integer cardPoint, HttpServletRequest request) {
        return customerService.changeCardPoints(cardPoint, request.getUserPrincipal().getName());
    }

    @GetMapping
    @RequestMapping("/{user_id}")
    public CustomerVOFull show(@PathVariable("user_id") Long userId) {
        return customerService.retrieveCustomerData(userId);
    }

    @GetMapping
    @RequestMapping("/byemail/{user_email}")
    public CustomerVOFull showEmail(@PathVariable("user_email") String userEmail) {
        return customerService.retrieveCustomerData(userEmail);
    }

    // нужно проверить клиентов, использует ли кто-то этот URL
    @GetMapping
    @RequestMapping("/details")
    public CustomerVOFull echoDetails(HttpServletRequest request){
        if(request.getUserPrincipal() != null) {
            return customerService.retrieveCustomerData(request.getUserPrincipal().getName());
        }
        return null;
    }

}
