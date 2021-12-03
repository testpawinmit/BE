package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "SUPPLIER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)

@Data
public class Supplier {

    /**
     * 1
     */
    @Id
    @Column(name = "SUPP_ID")
    @NotNull
    private long suppId;

    /**
     * 2
     */
    @Column(name = "SUPP_CODE", length = 30)
    @NotNull
    private String suppCode;

    /**
     * 3
     */
    @Column(name = "SUPP_NIC", length = 30)
    private String suppNic;

    /**
     * 4
     */
    @Column(name = "SUPP_ADDRESS", length = 30)
    private String suppAddress;

    /**
     * 5
     */
    @Column(name = "SUPP_PHONE", length = 50)
    private String suppPhone;


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
