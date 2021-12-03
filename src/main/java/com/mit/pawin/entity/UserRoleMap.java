package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "USER_ROLE_MAP")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class UserRoleMap implements Serializable {

	@Id
	@Column(name = "URM_ID")
	@NotNull
	private long urmId;

	@Column(name = "PARENT_USR_ROLE_CODE", length = 30)
	@NotNull
	private String parentUsrRoleCode;

	@Column(name = "CHILD_USR_ROLE_CODE", length = 30)
	@NotNull
	private String childUsrRoleCode;

}
