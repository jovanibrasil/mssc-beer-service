package com.jovani.msscbeerservice.web.controller;

import com.jovani.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return ResponseEntity.ok(BeerDto.builder().build());
    }

    @PostMapping
    public ResponseEntity saveBeer(@RequestBody BeerDto beerDto){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateBeer(@RequestBody BeerDto beerDto){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
