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
@Table(name = "DIETARY")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Dietary implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "DIET_ID")
    @NotNull
    private long dietId;

    /**
     * 2
     */
    @Column(name = "DIET_CODE", length = 30)
    @NotNull
    private String dietCode;

    /**
     * 3
     */
    @Column(name = "PET_CODE", length = 30)
    @NotNull
    private String petCode;

    /**
     * 4
     */
    @Column(name = "OWN_FOOD", length = 30)
    @NotNull
    private String ownFood;

    /**
     * 5
     */
    @Column(name = "FEED_TIME_MORNING", length = 50)
    private String feedTimeMorning;

    /**
     * 6
     */
    @Column(name = "FEED_TIME_AFTERNOON", length = 50)
    private String feedTimeAfternoon;

    /**
     * 7
     */
    @Column(name = "FEED_TIME_EVENING", length = 50)
    private String feedTimeEvening;

    /**
     * 8
     */
    @Column(name = "FEED_ALONE", length = 50)
    @NotNull
    private String feedAlone;



    /**
     * 9
     */
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 10
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 11
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 12
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

    @Column(name = "FOOD_TYPE", length = 30)
    private String foodType;

}
