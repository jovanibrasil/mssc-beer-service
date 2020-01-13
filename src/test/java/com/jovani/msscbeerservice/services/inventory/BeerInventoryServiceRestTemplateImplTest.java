package com.jovani.msscbeerservice.services.inventory;

import com.jovani.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    private BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {
        UUID uuid = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
        Integer qoh = this.beerInventoryService.getOnHandInventory(uuid);
        System.out.println(qoh);
    }

}