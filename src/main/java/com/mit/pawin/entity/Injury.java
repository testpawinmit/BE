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
@Table(name = "INJURY")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Injury implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "INJURY_ID")
    @NotNull
    private long injuryId;

    /**
     * 2
     */
    @Column(name = "INJURY_CODE", length = 30)
    @NotNull
    private String injuryCode;

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
    @Column(name = "INJURY_DES", length = 15)
    @NotNull
    private String injuryDes;



    /**
     * 9
     */
    @Column(name = "INJURY_OCCURRED", length = 30)
    @NotNull
    private Date injuryOccurred;


    /**
     * 10
     */
    @Column(name = "INJURY_IMPACT", length = 30)
    @NotNull
    private String injuryImpact;

    /**
     * 11
     */
    @Column(name = "INJURY_UNTIL", length = 30)
    @NotNull
    private Date injuryUntil;


}
