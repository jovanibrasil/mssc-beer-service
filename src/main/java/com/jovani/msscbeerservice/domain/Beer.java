package com.jovani.msscbeerservice.domain;

import com.jovani.msscbeerservice.web.model.BeerStyleEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version // for optimistic locking
    private Integer version;
    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdDate;
    @UpdateTimestamp
    private OffsetDateTime lastModifiedDate;
    private String beerName;
    private String beerStyle;
    private BigDecimal price;
    private Integer quantityOnHand;
    @Column(unique = true)
    private Long upc;
    private Integer quantityToBrew;

}
