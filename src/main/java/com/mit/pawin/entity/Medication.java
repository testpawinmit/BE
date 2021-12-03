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
@Table(name = "MEDICATION")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Medication implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "MED_ID")
    @NotNull
    private long medId;

    /**
     * 2
     */
    @Column(name = "MED_CODE", length = 30)
    @NotNull
    private String medCode;

    /**
     * 3
     */
    @Column(name = "PET_CODE", length = 30)
    @NotNull
    private String petCode;

     /**
     * 6
     */
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 3
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 4
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 5
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

     /**
     * 6
     */
    @Column(name = "MED_DOSAGE", length = 30)
    @NotNull
    private String medDosage;

    /**
     * 7
     */
    @Column(name = "MED_NAME", length = 30)
    @NotNull
    private String medName;


    /**
     * 8
     */
    @Column(name = "MED_FREQUENCY", length = 30)
    @NotNull
    private String medFrequency;

    /**
     * 9
     */
    @Column(name = "MED_AM", length = 30)
    @NotNull
    private String medAM;

    /**
     * 10
     */
    @Column(name = "MED_MID", length = 30)
    @NotNull
    private String medMid;

    /**
     * 11
     */
    @Column(name = "MED_PM", length = 30)
    @NotNull
    private String medPM;

    /**
     * 12
     */
    @Column(name = "MED_Until", length = 30)
    @NotNull
    private Date medUntil;
}
