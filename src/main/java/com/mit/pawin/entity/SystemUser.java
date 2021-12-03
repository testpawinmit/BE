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

@Entity
@Table(name = "SYSTEM_USER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class SystemUser implements Serializable {

	@Id
	@Column(name = "SYS_USR_ID")
	@NotNull
	private long sysUsrId;//

	@Column(name = "SYS_USR_CODE", length = 30)
	@NotNull
	private String sysUsrCode;//

	@Column(name = "USERNAME", length = 30)
	private String username;//*

	@Column(name = "PASSWORD", length = 100)
	private String password;//*

	@Column(name = "EMP_CODE", length = 30)
	private String empCode;//*

	@Column(name = "APP_CODE", length = 30)
	private String appCode;//*

	@Column(name = "PASS_EXPIR")
	private Date passExpir;//*

	@Column(name = "IS_ACTIVE")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isActive;//

	@Column(nullable = false, updatable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;//

	@Column(name = "CREATED_BY", length = 30)
	private String createdBy;//

	@Column(name = "UPDATED_AT")
	private Date updatedAt;//*

	@Column(name = "UPDATED_BY", length = 30)
	private String updatedBy;//*

	@Column(name = "USER_ROLE_CODE", length = 30)
	private String userRoleCode;//*

	@Column(name = "USER_LOGIN_COUNT")
	private int userLoggingCount;

	@Column(name = "PASS_EXPIR_STATUS")
	private EmailSendStatus passExpirStatus;

	public enum EmailSendStatus {
		NOT_SENT, //0
		SENT, //1
		ERROR //2
	}
}
