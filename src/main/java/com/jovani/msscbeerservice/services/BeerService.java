package com.jovani.msscbeerservice.services;

import com.jovani.msscbeerservice.web.model.BeerDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID uuid);
    BeerDto saveBeer(BeerDto beerDto);
    BeerDto updateBeer(BeerDto beerDto);

}
