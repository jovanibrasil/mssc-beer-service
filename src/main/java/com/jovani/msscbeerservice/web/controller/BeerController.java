package com.jovani.msscbeerservice.web.controller;

import com.jovani.msscbeerservice.web.model.BeerDto;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
        return ResponseEntity.ok(BeerDto.builder()
                .id(beerId)
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA)
                .quantityOnHand(12)
                .upc(337010000001L)
                .price(new BigDecimal("12.95"))
                .build());
    }

    @PostMapping
    public ResponseEntity saveBeer(@RequestBody @Valid BeerDto beerDto){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateBeer(@RequestBody @Valid BeerDto beerDto){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
