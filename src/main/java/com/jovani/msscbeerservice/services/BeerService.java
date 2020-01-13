package com.jovani.msscbeerservice.services;

import com.jovani.msscbeerservice.web.model.BeerDto;
import com.jovani.msscbeerservice.web.model.BeerPagedList;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID uuid);
    BeerDto saveBeer(BeerDto beerDto);
    BeerDto updateBeer(BeerDto beerDto);
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of);
}
