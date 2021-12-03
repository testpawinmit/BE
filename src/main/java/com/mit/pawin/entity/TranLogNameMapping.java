package com.mit.pawin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TRAN_LOG_NAME_MAPPING")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
		allowGetters = true)
@Data
public class TranLogNameMapping implements Serializable {

	/**
	 * 1
	 */
	@Id
	@Column(name = "NAME_MAP_ID")
	@NotNull
	private long nameMapId;

	/**
	 * 2
	 */
	@Column(name = "NAME_MAP_CODE", length = 30)
	@NotNull
	private String nameMapCode;

	/**
     *3
	 */
	@Column(name = "FILE_NAME", length = 45)
	@NotNull
	private String fileName;

	/**
	 * 4
	 */
	@Column(name = "ENCRYPTED_NAME", length = 45)
	@NotNull
	private String encryptedName;

	/**
	 *5
	 */
	@Column(nullable = false, updatable = false, name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	/**
	 *6
	 */
	@Column(name = "CREATED_BY", length = 30)
	@NotNull
	private String createdBy;

}
