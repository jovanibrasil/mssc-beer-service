package com.jovani.msscbeerservice.repositories;

import com.jovani.msscbeerservice.domain.Beer;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
    Optional<Beer> findByUpc(Long upc);
}
