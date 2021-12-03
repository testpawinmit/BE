package com.mit.pawin.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "APP_TYPE")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AppType implements Serializable {

	@Id
	@Column(name = "APP_TYPE_ID")
	@NotNull
	private long appTypeId;

	@Column(name = "APP_TYPE_CODE", length = 30)
	@NotNull
	private String appTypeCode;

	@Column(name = "APP_TYPE_NAME", length = 50)
	@NotNull
	private String appTypeName;

}
