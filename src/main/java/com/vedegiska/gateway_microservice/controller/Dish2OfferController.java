package com.vedegiska.gateway_microservice.controller;

import com.vedegiska.gateway_microservice.dto.Dish2OfferVO;
import com.vedegiska.gateway_microservice.service.inter.IDish2OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/dish2offer")
@RequiredArgsConstructor
public class Dish2OfferController {
    private final IDish2OfferService dish2OfferService;

    @GetMapping
    @RequestMapping("/{offer_id}")
    public ResponseEntity<? super Object> showTrash(@PathVariable("offer_id") long offerId) {
        return dish2OfferService.getTrash(offerId);
    }

    @PostMapping
    @RequestMapping("/add_dishes/{offer_id}")
    public ResponseEntity<Void> changeProd(@RequestBody Set<Dish2OfferVO> trash,
                                           @PathVariable("offer_id") long offerId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    @RequestMapping("/addProduct")
    public ResponseEntity<Dish2OfferVO> addUpdProd(@RequestBody Dish2OfferVOAddRequest d2oVO, HttpServletRequest request) {
        Dish2OfferVO d2o = dish2OfferService.addOrUpdTrashD2O(d2oVO);
        return ResponseEntity.ok(d2o);
    }

    @DeleteMapping
    @RequestMapping("/delete/{d2o_id}")
    public void deleteProd(@PathVariable("d2o_id") long d2oId, HttpServletRequest request) {
        dish2OfferRepository.deleteById(d2oId);
    }
}
