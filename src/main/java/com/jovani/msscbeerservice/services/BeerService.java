package com.jovani.msscbeerservice.services;

import com.jovani.msscbeerservice.web.model.BeerDto;
import com.jovani.msscbeerservice.web.model.BeerPagedList;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID uuid, Boolean showInventory);
    BeerDto saveBeer(BeerDto beerDto);
    BeerDto updateBeer(BeerDto beerDto);
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventory);
    BeerDto getByUpc(Long upc, Boolean showInventory);
}
