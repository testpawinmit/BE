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
@Table(name = "CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Customer implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "CUST_ID")
    @NotNull
    private long custId;

    /**
     * 2
     */
    @Column(name = "CUST_CODE", length = 30)
    @NotNull
    private String custCode;

    /**
     * 3
     */
    @Column(name = "FIRST_NAME", length = 30)
    private String custFirstName;

    /**
     * 4
     */
    @Column(name = "LAST_NAME", length = 30)
    private String custLastName;

    /**
     * 5
     */
    @Column(name = "EMAIL", length = 50)
    private String custEmail;


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
    @Column(name = "NIC", length = 30)
    private String custNic;


    /**
     * 11
     */
    @Column(name = "ADDRESS", length = 100)
    private String custAddress;



    /**
     * 12
     */
    @Column(name = "PHONE_NO", length = 15)
    private String custPhone;



    /**
     * 13
     */
    @Column(name = "LOCATION", length = 30)
    private String location;

}
