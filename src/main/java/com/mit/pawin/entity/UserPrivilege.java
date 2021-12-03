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
@Table(name = "USER_PRIVILEGE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class UserPrivilege implements Serializable {

	@Id
	@Column(name = "USER_PRIVILEGE_ID")
	@NotNull
	private long userPrivilegeId;

	@Column(name = "USER_PRIVILEGE_CODE", length = 30)
	@NotNull
	private String userPrivilegeCode;

	@Column(name = "USER_PRIV_NAME", length = 50)
	@NotNull
	private String userPrivName;

	@Column(nullable = false, updatable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "CREATED_BY", length = 30)
	@NotNull
	private String createdBy;

	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Column(name = "UPDATED_BY", length = 30)
	private String updatedBy;

	@Column(name = "IS_ACTIVE", length = 2)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isActive;

	@Column(name = "LEVEL")
	private int level;

	@Column(name = "PARENT_LEVEL")
	private int parentLevel;

	@Column(name = "IS_ASSIGNED")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isAssigned;

}
