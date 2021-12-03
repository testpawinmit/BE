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
@Table(name = "MEDICAL_PHYSICAL_DISABILITIES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class MedicalPhysicalDisabilities implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "DISABLE_ID")
    @NotNull
    private long disableId;

    /**
     * 2
     */
    @Column(name = "DISABLE_CODE", length = 30)
    @NotNull
    private String disableCode;

    /**
     * 3
     */
    @Column(name = "PET_CODE", length = 30)
    @NotNull
    private String petCode;

    /**
     * 4
     */

    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 5
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 6
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 7
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

    /**
     * 8
     */
    @Column(name = "DISABLE_NAME", length = 30)
    @NotNull
    private String disableName;


    /**
     * 9
     */
    @Column(name = "DISABLE_TREATMENT", length = 100)
    @NotNull
    private String disableTreatment;

    }

