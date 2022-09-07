package com.govtech.viswa.entity;

import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Log4j2
public class BeforeSaveDoThis implements BeforeConvertCallback<InsertableEntity> {

    @Override
    public Publisher<InsertableEntity> onBeforeConvert(InsertableEntity entity, SqlIdentifier table) {
        entity.setCreatedTm(LocalDateTime.now());
        return Mono.just(entity);
    }


/*
    @Override
    public Publisher<InsertableEntity> onBeforeSave(InsertableEntity entity, OutboundRow row, SqlIdentifier table) {
        LocalDateTime localDateTime = LocalDateTime.now();
        row.put(SqlIdentifier.unquoted("createdTm"), Parameter.from(LocalDateTime.now()));
        return Mono.just(entity);
    }
    */
}
