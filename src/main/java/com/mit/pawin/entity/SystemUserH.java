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
@Table(name = "SYSTEM_USER_H")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class SystemUserH implements Serializable {

	@Id
	@Column(name = "SYS_USR_H_ID")
	@NotNull
	private long sysUsrHId;

	@Column(name = "SYS_USR_CODE")
	@NotNull
	private String sysUsrCode;

	@Column(name = "USERNAME")
	@NotNull
	private String username;

	@Column(name = "EMP_CODE")
	@NotNull
	private String empCode;

	@Column(name = "APP_CODE")
	@NotNull
	private String appCode;

	@Column(name = "IS_ACTIVE")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isActive;

	@Column(name = "USER_ROLE_CODE")
	@NotNull
	private String userRoleCode;

	@Column(nullable = false, updatable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "CREATED_BY")
	@NotNull
	private String createdBy;

	@Column(name = "USER_LOGIN_COUNT")
	@NotNull
	private int userLoggingCount;

	@Column(name = "PASS_EXPIR_STATUS")
	@NotNull
	private int passExpirStatus;

}
