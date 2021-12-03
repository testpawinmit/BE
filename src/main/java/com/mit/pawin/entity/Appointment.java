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
@Table(name = "APPOINTMENT")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Appointment implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "APP_ID")
    @NotNull
    private long appId;

    /**
     * 2
     */
    @Column(name = "APP_CODE", length = 30)
    @NotNull
    private String appCode;

    /**
     * 3
     */
    @Column(name = "SERVICE_CAT_CODE", length = 30)
    private String serviceCatCode;

    /**
     * 4
     */
    @Column(name = "SERVICE_CODE", length = 30)
    private String serviceCode;

    /**
     * 5
     */
    @Column(name = "ROOM_CODE", length = 50)
    private String roomCode;


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
    @Column(name = "EMP_CODE", length = 30)
    private String empCode;


    /**
     * 11
     */
    @Column(name = "CHECK_IN_DATE", length = 100)
    private Date checkInDate;



    /**
     * 12
     */
    @Column(name = "CHECK_IN_TIME", length = 15)
    private String checkInTime;



    /**
     * 13
     */
    @Column(name = "CHECK_OUT_DATE", length = 30)
    private Date checkOutDate;

    /**
     * 14
     */
    @Column(name = "TOTAL_PRICE", length = 30)
    private String totalPrice;

    @Column(name = "PAYMENT_TYPE", length = 30)
    private String paymentType;

    @Column(name = "PORTAL_STATUS", length = 30)
    private String portalStatus;


}
