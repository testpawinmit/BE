package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "BREED")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)

@Data
public class Breed {

    /**
     * 1
     */
    @Id
    @Column(name = "BREED_ID")
    @NotNull
    private long breedId;

    /**
     * 2
     */
    @Column(name = "BREED_CODE", length = 30)
    @NotNull
    private String breedCode;

    /**
     * 3
     */
    @Column(name = "BREED_NAME", length = 30)
    private String breedName;

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
