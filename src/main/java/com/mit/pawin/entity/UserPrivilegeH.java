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
@Table(name = "USER_PRIVILEGE_H")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class UserPrivilegeH implements Serializable {

	@Id
	@Column(name = "USER_PRIVILEGE_H_ID")
	@NotNull
	private long userPrivilegeHId;

	@Column(name = "USER_PRIVILEGE_CODE", length = 30)
	@NotNull
	private String userPrivilegeCode;

	@Column(name = "USER_PRIV_NAME", length = 50)
	@NotNull
	private String userPrivName;

	@Column(name = "HIERARCHY_STATUS", length = 10)
	@NotNull
	private String hierarchyStatus;

	@Column(nullable = false, updatable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "CREATED_BY", length = 30)
	@NotNull
	private String createdBy;

	@Column(name = "IS_ACTIVE", length = 2)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isActive;

}
