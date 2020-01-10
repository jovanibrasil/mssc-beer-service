package com.jovani.msscbeerservice.web.controller;

import com.jovani.msscbeerservice.services.BeerService;
import com.jovani.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
        return ResponseEntity.ok(
                this.beerService.getById(beerId)
        );
    }

    @PostMapping
    public ResponseEntity saveBeer(@RequestBody @Valid BeerDto beerDto){
        this.beerService.saveBeer(beerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateBeer(@RequestBody @Valid BeerDto beerDto){
        this.beerService.updateBeer(beerDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
