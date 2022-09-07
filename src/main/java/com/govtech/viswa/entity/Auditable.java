package com.govtech.viswa.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

//    @Column("createdby")
    @CreatedBy
    private U createdBy;

//    @Column("createdtm")
    @CreatedDate
    private LocalDateTime createdTm;

//    @Column("updatedby")
    @LastModifiedBy
    private U updatedBy;

//    @Column("updatedtm")
    @LastModifiedDate
    private LocalDateTime updatedTm;

    @PrePersist
    public void onCreate() {
        this.createdTm = LocalDateTime.now();
        this.updatedTm = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedTm = LocalDateTime.now();
    }
}
