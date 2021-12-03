package com.mit.pawin.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACTION_LOG_COUNT")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TransactionLogCount implements Serializable {

	@Id
	@Column(name = "LOG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long logId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "RECORD_COUNT")
	private int recordCount;

	@Column(name = "LOG_DATE")
	private String logDate;

}
