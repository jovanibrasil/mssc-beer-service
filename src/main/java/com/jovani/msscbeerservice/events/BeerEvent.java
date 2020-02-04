package com.jovani.msscbeerservice.events;

import com.jovani.msscbeerservice.web.model.BeerDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerEvent {

    public static final long serialVersionUID = -616846359104522108L;
    private BeerDto beerDto;

}
