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
@Table(name = "PET_APPOINTMENT")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class PetAppointment implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "PET_APP_ID")
    @NotNull
    private long petAppId;

    /**
     * 2
     */
    @Column(name = "PET_APP_CODE", length = 30)
    @NotNull
    private String petAppCode;

    /**
     * 3
     */
    @Column(name = "PET_CODE", length = 30)
    private String petCode;

    /**
     * 4
     */
    @Column(name = "APP_CODE", length = 30)
    private String appCode;


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
