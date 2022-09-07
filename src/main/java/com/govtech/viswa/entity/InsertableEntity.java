package com.govtech.viswa.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
public abstract class InsertableEntity {

    @Column("createdtm")
    @CreatedDate
    private LocalDateTime createdTm;

    @Column("createdby")
    @CreatedBy
    private String createdBy;


}
