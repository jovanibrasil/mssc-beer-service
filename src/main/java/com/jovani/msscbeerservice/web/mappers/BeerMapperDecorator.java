package com.jovani.msscbeerservice.web.mappers;

import com.jovani.msscbeerservice.domain.Beer;
import com.jovani.msscbeerservice.services.inventory.BeerInventoryService;
import com.jovani.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return this.mapper.beerDtoToBeer(beerDto);
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        BeerDto dto = this.mapper.beerToBeerDto(beer);
        Integer quantityOnHand = this.beerInventoryService.getOnHandInventory(beer.getId());
        dto.setQuantityOnHand(quantityOnHand);
        return dto;
    }
}
