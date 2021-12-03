package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "SUPPLIER_RETAIL")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class SupplierRetail {

    /**
     * 1
     */
    @Id
    @Column(name = "SUPP_RETAIL_ID")
    @NotNull
    private long suppRetailId;

    /**
     * 2
     */
    @Column(name = "SUPP_CODE", length = 30)
    @NotNull
    private String suppCode;

    /**
     * 3
     */
    @Column(name = "RETAIL_CODE", length = 30)
    private String retailCode;

    /**
     * 4
     */
    @Column(name = "RETAIL_NAME", length = 30)
    private String retailName;

    /**
     * 5
     */
    @Column(name = "MAX_COUNT", length = 50)
    private String maxCount;

    @Column(name = "WHOLE_SALE_PRICE", length = 50)
    private String wholeSalePrice;


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

    @Column(name = "IS_APPROVED")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull
    private Boolean isApproved;

}
