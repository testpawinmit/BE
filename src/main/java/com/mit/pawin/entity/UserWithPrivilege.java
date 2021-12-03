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
@Table(name = "USER_WITH_PRIVILEGE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class UserWithPrivilege implements Serializable {

	@Id
	@Column(name = "USR_WITH_PRIV_ID")
	@NotNull
	private long usrWithPrivId;

	@Column(name = "USR_WITH_PRIV_CODE", length = 30)
	@NotNull
	private String usrWithPrivCode;

	@Column(name = "USER_ROLE_CODE", length = 30)
	@NotNull
	private String userRoleCode;

	@Column(name = "USER_PRIVILEGE_CODE", length = 30)
	@NotNull
	private String userPrivilegeCode;

	@Column(nullable = false, updatable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "CREATED_BY", length = 30)
	@NotNull
	private String createdBy;

	@Column(name = "IS_ADDED")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isAdded;

	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Column(name = "UPDATED_BY", length = 30)
	private String updatedBy;

}
