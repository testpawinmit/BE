package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PET_VACCINATION")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class PetVaccination implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "PET_VACC_ID")
    @NotNull
    private long petVaccId;

    /**
     * 2
     */
    @Column(name = "PET_VACC_CODE", length = 30)
    @NotNull
    private String petVaccCode;

    /**
     * 3
     */
    @Column(name = "PET_CODE", length = 30)
    @NotNull
    private String petCode;

    /**
     * 4
     */
    @Column(name = "VACC_CODE", length = 30)
    @NotNull
    private String vaccCode;

    /**
     * 5
     */
    @Column(name = "EXPIRATION_DATE", length = 50)
    @NotNull
    private Date expirationDate;

    /**
     * 6
     */
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 7
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 8
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 9
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;




}
