package com.jovani.msscbeerservice.services;

import com.jovani.msscbeerservice.domain.Beer;
import com.jovani.msscbeerservice.repositories.BeerRepository;
import com.jovani.msscbeerservice.web.exceptions.InvalidArgumentException;
import com.jovani.msscbeerservice.web.exceptions.NotFoundException;
import com.jovani.msscbeerservice.web.mappers.BeerMapper;
import com.jovani.msscbeerservice.web.model.BeerDto;
import com.jovani.msscbeerservice.web.model.BeerPagedList;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#uuid", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getById(UUID uuid, Boolean showInventoryOnHand) {
        Beer beer = this.beerRepository.findById(uuid).orElseThrow(NotFoundException::new);
        if(showInventoryOnHand) return this.beerMapper.beerToBeerDtoWithInventory(beer);
        return this.beerMapper.beerToBeerDto(beer);
    }

    @Cacheable(cacheNames = "beerUpcCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getByUpc(Long upc, Boolean showInventoryOnHand) {
        Beer beer = this.beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new);
        if(showInventoryOnHand) return this.beerMapper.beerToBeerDtoWithInventory(beer);
        return this.beerMapper.beerToBeerDto(beer);
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

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        log.info("Listing beers ...");

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)){
            beerPage = this.beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)){
            beerPage = this.beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)){
            beerPage = this.beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = this.beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(showInventoryOnHand ? beerMapper::beerToBeerDtoWithInventory : beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest
                    .of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
        return beerPagedList;

    }

}
