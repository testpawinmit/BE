package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "RESOURCES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)

@Data
public class Resources {
    /**
     * 1
     */
    @Id
    @Column(name = "ROOM_ID")
    @NotNull
    private long roomId;

    /**
     * 2
     */
    @Column(name = "ROOM_CODE", length = 30)
    @NotNull
    private String roomCode;

    /**
     * 3
     */
    @Column(name = "ROOM_NAME", length = 30)
    private String roomName;

     /**
     * 5
     */
    @Column(name = "MAX_WEIGHT", length = 50)
    private String maxWeight;

    /**
     * 6
     */
    @Column(name = "CLEAN_NEEDED", length = 50)
    private String cleanNeeded;


    /**
     * 7
     */
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 8
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 9
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 10
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

}
