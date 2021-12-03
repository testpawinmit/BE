package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Data
public class Employee implements Serializable {

    /**
     * 1
     */
    @Id
    @Column(name = "EMP_ID")
    @NotNull
    private long empId;

    /**
     * 2
     */
    @Column(name = "EMP_CODE", length = 30)
    @NotNull
    private String empCode;

    /**
     * 3
     */
    @Column(name = "EMP_FIRST_NAME", length = 30)
    private String empFirstName;

    /**
     * 4
     */
    @Column(name = "EMP_LAST_NAME", length = 30)
    private String empLastName;

    /**
     * 5
     */
    @Column(name = "EMP_FULL_NAME", length = 100)
    private String empFullName;

    /**
     * 6
     */
    @Column(name = "EMP_EMAIL", length = 50)
    private String empEmail;

    /**
     * 7
     */
    @Column(name = "EMP_NO", length = 30)
    private String empNo;

    /**
     * 7
     */
    @Column(name = "IS_ACTIVE")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    /**
     * 8
     */
    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * 9
     */
    @Column(name = "CREATED_BY", length = 30)
    @NotNull
    private String createdBy;

    /**
     * 10
     */
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    /**
     * 11
     */
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

    /**
     * 12
     */
    @Column(name = "EMP_SEX", length = 30)
    private String empSex;

    /**
     * 13
     */
    @Column(name = "EMP_CIVIL_STATUS", length = 30)
    private String empCivilStatus;

    /**
     * 14
     */
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    /**
     * 15
     */
    @Column(name = "EMP_NIC", length = 30)
    private String empNic;

    /**
     * 16
     */
    @Column(name = "EPF_NO", length = 30)
    private String epfNo;

    /**
     * 18
     */
    @Column(name = "DATE_OF_JOIN")
    private Date dateOfJoin;

    /**
     * 17
     */
    @Column(name = "MACHINE_STATUS", length = 30)
    private String machineStatus;

    /**
     * 18
     */
    @Column(name = "MACHINE_CODE", length = 30)
    private String machineCode;

    /**
     * 19
     */
    @Column(name = "CONFIRM_DATE")
    private Date confirmDate;

    /**
     * 20
     */
    @Column(name = "EMP_STATUS", length = 5)
    private EmpStatus empStatus;

    /**
     * 21
     */
    @Column(name = "DATE_LEFT")
    private Date dateLeft;

    /**
     * 22
     */
    @Column(name = "ADDR_LINE_1", length = 100)
    private String addrLine1;

    /**
     * 23
     */
    @Column(name = "ADDR_LINE_2", length = 100)
    private String addrLine2;

    /**
     * 24
     */
    @Column(name = "CITY", length = 30)
    private String city;

    /**
     * 25
     */
    @Column(name = "POSTAL_CODE", length = 30)
    private String postalCode;

    /**
     * 26
     */
    @Column(name = "TEL_RESIDENCE", length = 15)
    private String telResidence;

    /**
     * 27
     */
    @Column(name = "TEL_MOBILE", length = 15)
    private String telMobile;

    /**
     * 28
     */
    @Column(name = "BANK_ACNT_NO", length = 30)
    private String bankAcntNo;

    /**
     * 29
     */
    @Column(name = "EMP_COMPANY", length = 50)
    private String empCompany;

    /**
     * 30
     */
    @Column(name = "EMP_BRANCH", length = 30)
    private String empBranch;

    /**
     * 31
     */
    @Column(name = "EMP_DEPARTMENT", length = 30)
    private String empDepartment;

    /**
     * 32
     */
    @Column(name = "EMP_SECTION", length = 30)
    private String empSection;

    /**
     * 33
     */
    @Column(name = "EMP_GRADE", length = 30)
    private String empGrade;

    /**
     * 34
     */
    @Column(name = "EMP_GROUP", length = 30)
    private String empGroup;

    /**
     * 35
     */
    @Column(name = "EMP_JOB_TITLE", length = 30)
    private String empJobTitle;

    /**
     * 36
     */
    @Column(name = "EMP_NATIONALITY", length = 30)
    private String empNationality;

    /**
     * 37
     */
    @Column(name = "EMP_RELIGION", length = 30)
    private String empReligion;

    /**
     * 38
     */
    @Column(name = "FIXED_SHIFT", length = 30)
    private String fixedShift;

    /**
     * 39
     */
    @Column(name = "SHIFT_GROUP", length = 30)
    private String shiftGroup;

    /**
     * 40
     */
    @Column(name = "CALENDAR_TYPE_CODE", length = 30)
    private String calendarTypeCode;

    /**
     * 41
     */
    @Column(name = "OT_TYPE", length = 30)
    private String otType;

    /**
     * 42
     */
    @Column(name = "LEAVE_TYPE", length = 30)
    private String leaveType;

    /**
     * 43
     */
    @Column(name = "PROFILE_IMG_NAME", length = 50)
    private String profileImgName;

    /**
     * 44
     */
    @Column(name = "TITLE", length = 5)
    private String title;

    /**
     * 45
     */
    @Column(name = "EMP_CONFIRMATION", length = 5)
    private EmpConfirmation empConfirmation;

    /**
     * 46
     */
    @Column(name = "EMP_FUTURE_CONFIRM_DATE")
    private Date empFutureConfirmDate;

    /**
     * 47
     */
    @Column(name = "IMMEDIATE_BOSS_CODE", length = 30)
    private String immediateBossCode;

    public enum EmpStatus {
        ACTIVE, //0
        INACTIVE, //1
        SUSPENDED, //2
        RESIGN //3
    }

    public enum EmpConfirmation {
        PROBATION, //0
        PERMANENT, //1
        INTERNSHIP, //2
        CONTRACT_BASIS //3
    }

    /**
     * 48
     */
    @Column(name = "COMPNY_CODE", length = 30)
    private String compnyCode;

    /**
     * 49
     */
    @Column(name = "BRANCH_CODE", length = 30)
    private String branchCode;

    /**
     * 50
     */
    @Column(name = "DEPT_CODE", length = 30)
    private String deptCode;

    /**
     * 51
     */
    @Column(name = "SECTION_CODE", length = 30)
    private String sectionCode;

    /**
     * 52
     */
    @Column(name = "GRADE_CODE", length = 30)
    private String gradeCode;

    /**
     * 53
     */
    @Column(name = "E_GROUP_CODE", length = 30)
    private String egroupCode;

    /**
     * 54
     */
    @Column(name = "JOB_TITLE_CODE", length = 30)
    private String jobTitleCode;

    /**
     * 55
     */
    @Column(name = "NATION_CODE", length = 30)
    private String nationCode;

    /**
     * 56
     */
    @Column(name = "REL_CODE", length = 30)
    private String relCode;

    @Column(name = "EMP_ADDRESS", length = 30)
    private String empAddress;

    @Column(name = "EMP_PHONE", length = 30)
    private String empPhone;

    @Column(name = "EMP_NAME", length = 30)
    private String empName;

}
