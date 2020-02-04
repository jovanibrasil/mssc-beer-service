package com.jovani.msscbeerservice.services.brewing;

import com.jovani.msscbeerservice.config.JmsConfig;
import com.jovani.msscbeerservice.domain.Beer;
import com.jovani.msscbeerservice.events.BrewBeerEvent;
import com.jovani.msscbeerservice.repositories.BeerRepository;
import com.jovani.msscbeerservice.services.inventory.BeerInventoryService;
import com.jovani.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invIOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Min on hand is {}.", invIOH);
            log.debug("Inventory is {}.", invIOH);

            if(beer.getMinOnHand() >= invIOH){
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }

}
