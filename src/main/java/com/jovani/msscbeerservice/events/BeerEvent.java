package com.jovani.msscbeerservice.events;

import com.jovani.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent {

    public static final long serialVersionUID = -616846359104522108L;

    private final BeerDto beerDto;

}
