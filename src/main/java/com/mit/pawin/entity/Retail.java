package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "RETAIL")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)

@Data
public class Retail {
    /**
     * 1
     */
    @Id
    @Column(name = "RETAIL_ID")
    @NotNull
    private long retailId;

    /**
     * 2
     */
    @Column(name = "RETAIL_CODE", length = 30)
    @NotNull
    private String retailCode;

    /**
     * 3
     */
    @Column(name = "RETAIL_CAT_CODE", length = 30)
    private String retailCatCode;

    /**
     * 4
     */
    @Column(name = "RETAIL_NAME", length = 30)
    private String retailName;

    /**
     * 5
     */
    @Column(name = "RETAIL_PRICE", length = 50)
    private String retailPrice;

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
