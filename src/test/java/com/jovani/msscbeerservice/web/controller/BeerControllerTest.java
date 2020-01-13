package com.jovani.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jovani.msscbeerservice.services.BeerService;
import com.jovani.msscbeerservice.web.model.BeerDto;
import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BeerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BeerService beerService;

    private ObjectMapper objectMapper;

    private BeerDto beerDto;

    private BeerController beerController;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        this.beerController = new BeerController(this.beerService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.beerController).build();
        this.beerDto = BeerDto.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA)
                .quantityOnHand(12)
                .upc(337010000001L)
                .price(new BigDecimal("12.95"))
                .build();
    }

    @Test
    void getBeer() throws Exception {
        when(this.beerService.getById(any(), any())).thenReturn(this.beerDto);
        this.mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        String beerDtoJson = this.objectMapper.writeValueAsString(beerDto);
        when(this.beerService.saveBeer(any())).thenReturn(this.beerDto);
        this.mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        String beerDtoJson = this.objectMapper.writeValueAsString(beerDto);
        when(this.beerService.updateBeer(any())).thenReturn(this.beerDto);
        this.mockMvc.perform(put("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}