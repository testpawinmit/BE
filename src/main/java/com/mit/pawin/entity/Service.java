package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "SERVICE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Service {
    /**
     * 1
     */
    @Id
    @Column(name = "SERVICE_ID")
    @NotNull
    private long serviceId;

    /**
     * 2
     */
    @Column(name = "SERVICE_CAT_CODE", length = 30)
    @NotNull
    private String serviceCatCode;

    /**
     * 2
     */
    @Column(name = "SERVICE_CODE", length = 30)
    @NotNull
    private String serviceCode;

    /**
     * 3
     */
    @Column(name = "SERVICE_NAME", length = 30)
    private String serviceName;

    /**
     * 4
     */
    @Column(name = "SERVICE_PRICE", length = 30)
    private String servicePrice;

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
}
