package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE_SCHEDULE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class EmployeeSchedule {
    /**
     * 1
     */
    @Id
    @Column(name = "EMP_SCHEDULE_ID")
    @NotNull
    private long empScheduleId;

    /**
     * 2
     */
    @Column(name = "EMP_CODE", length = 30)
    @NotNull
    private String empCode;

    /**
     * 3
     */
    @Column(name = "DAY", length = 30)
    @NotNull
    private String day;

    /**
     * 4
     */
    @Column(name = "START_TIME", length = 30)
    @NotNull
    private String startTime;

    /**
     * 5
     */
    @Column(name = "END_TIME", length = 30)
    @NotNull
    private String endTime;


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
