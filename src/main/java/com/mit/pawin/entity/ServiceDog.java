package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "SERVICE_DOG")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class ServiceDog {
    /**
     * 1
     */
    @Id
    @Column(name = "SERVICE_DOG_ID")
    @NotNull
    private long serviceDogId;

    /**
     * 2
     */
    @Column(name = "SERVICE_DOG_CODE", length = 30)
    @NotNull
    private String serviceDogCode;

    /**
     * 3
     */
    @Column(name = "SERVICE_DOG_NAME", length = 30)
    private String serviceDogName;

    /**
     * 4
     */
    @Column(name = "SERVICE_DOG_DOB", length = 50)
    private Date serviceDogDob;


    /**
     * 5
     */

    @Column(name = "BREED_CODE", length = 30)
    private String breedCode;

    /**
     * 6
     */
    @Column(name = "COLOR_CODE", length = 30)
    private String colorCode;

    /**
     * 7
     */
    @Column(name = "NOTE", length = 50)
    private String note;

    /**
     * 8
     */

    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 9
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 10
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 11
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;



}
