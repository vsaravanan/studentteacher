package com.govtech.viswa.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @CreatedBy
    private U createdBy;

    @CreatedDate
    private LocalDateTime createdTm;

    @LastModifiedBy
    private U updatedBy;

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
