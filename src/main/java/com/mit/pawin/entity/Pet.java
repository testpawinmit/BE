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
@Table(name = "PET")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Pet implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "PET_ID")
    @NotNull
    private long petId;

    /**
     * 2
     */
    @Column(name = "PET_CODE", length = 30)
    @NotNull
    private String petCode;

    /**
     * 3
     */
    @Column(name = "CUST_CODE", length = 30)
    @NotNull
    private String custCode;

    /**
     * 4
     */
    @Column(name = "PET_NAME", length = 30)
    @NotNull
    private String petName;

    /**
     * 5
     */
    @Column(name = "DOB")
    @NotNull
    private Date dob;


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

    /**
     * 10
     */
    @Column(name = "TYPE", length = 30)
    @NotNull
    private String type;


    /**
     * 11
     */
    @Column(name = "WEIGHT", length = 100)
    @NotNull
    private String weight;



    /**
     * 12
     */
    @Column(name = "GENDER", length = 15)
    @NotNull
    private String gender;



    /**
     * 13
     */
    @Column(name = "BREED_CODE", length = 30)
    @NotNull
    private String breedCode;


    /**
     * 14
     */
    @Column(name = "COLOR_CODE", length = 30)
    @NotNull
    private String colorCode;

    @Transient
    private boolean checkElement = false;

}
