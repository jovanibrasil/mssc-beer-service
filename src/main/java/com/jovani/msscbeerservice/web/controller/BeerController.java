package com.jovani.msscbeerservice.web.controller;

import com.jovani.msscbeerservice.services.BeerService;
import com.jovani.msscbeerservice.web.model.BeerDto;
import com.jovani.msscbeerservice.web.model.BeerPagedList;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping
    public ResponseEntity<BeerPagedList> listBeers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer pageSize,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand
            ){
        BeerPagedList beerPagedList = this.beerService
                .listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
        return new ResponseEntity<>(beerPagedList, HttpStatus.OK);
    }

    @GetMapping("/{uuid:[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}}")
    public ResponseEntity<BeerDto> getBeer(
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventory,
            @PathVariable UUID uuid){
        return ResponseEntity.ok(
                this.beerService.getById(uuid, showInventory)
        );
    }

    @GetMapping("/{upc:[0-9]+}")
    public ResponseEntity<BeerDto> getBeer(
            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventory,
            @PathVariable Long upc){
        return ResponseEntity.ok(this.beerService.getByUpc(upc, showInventory));
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
