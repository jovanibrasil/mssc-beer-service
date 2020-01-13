package com.jovani.msscbeerservice.bootstrap;

import com.jovani.msscbeerservice.domain.Beer;
import com.jovani.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public static final Long BEER_1_UPC = 0631234200036L;
    public static final Long BEER_2_UPC = 0031234300010L;
    public static final Long BEER_3_UPC = 0013113315210L;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(this.beerRepository.count() == 0){
            this.beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .quantityOnHand(12)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .build());
            this.beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(100)
                    .quantityOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }

}
