package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "VETERINARY")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)

@Data
public class Veterinary {
    /**
     * 1
     */
    @Id
    @Column(name = "VET_ID")
    @NotNull
    private long vetId;

    /**
     * 2
     */
    @Column(name = "VET_CODE", length = 30)
    @NotNull
    private String vetCode;

    @Column(name = "VET_NAME", length = 30)
    private String vetName;

    /**
     * 3
     */
    @Column(name = "VET_PHONE", length = 30)
    private String vetPhone;

    /**
     * 4
     */
    @Column(name = "VET_ADDRESS", length = 30)
    private String vetAddress;

    /**
     * 5
     */
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 6
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 7
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 8
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;
}
