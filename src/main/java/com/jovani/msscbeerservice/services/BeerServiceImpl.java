package com.jovani.msscbeerservice.services;

import com.jovani.msscbeerservice.domain.Beer;
import com.jovani.msscbeerservice.repositories.BeerRepository;
import com.jovani.msscbeerservice.web.exceptions.InvalidArgumentException;
import com.jovani.msscbeerservice.web.exceptions.NotFoundException;
import com.jovani.msscbeerservice.web.mappers.BeerMapper;
import com.jovani.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID uuid) {
        return this.beerMapper.beerToBeerDto(
                this.beerRepository.findById(uuid).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        if(beerDto.getId() != null) throw new InvalidArgumentException("ID must not be null.");

        return this.beerMapper.beerToBeerDto(
                this.beerRepository.save(this.beerMapper.beerDtoToBeer(beerDto))
        );
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto) {
        Beer beer = this.beerRepository.findById(beerDto.getId()).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return this.beerMapper.beerToBeerDto(
                this.beerRepository.save(beer)
        );
    }

}
