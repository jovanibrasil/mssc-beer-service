package com.jovani.msscbeerservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime){
        if(offsetDateTime == null) return null;
        return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
    }

    public OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp){
        if(timestamp == null) return null;
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return OffsetDateTime.of(
                ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(),
                ldt.getHour(), ldt.getMinute(), ldt.getSecond(), ldt.getNano(), ZoneOffset.UTC
        );
    }

}
