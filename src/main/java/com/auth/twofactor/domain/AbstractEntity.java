package com.auth.twofactor.domain;

import com.auth.twofactor.configuration.SecurityUtils;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {

	private String createBy;
	private LocalDateTime createAt;
	private String updateBy;
	private LocalDateTime updateAt;
	@Version
	private long version;

	public String getCreateBy() {
		return createBy;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public long getVersion() {
		return version;
	}

	@PreUpdate
	public void beforeUpdate() {
		updateAt = LocalDateTime.now();
		updateBy = SecurityUtils.getLoginUtilisateur() != null ? SecurityUtils.getLoginUtilisateur() : "IMPORT_AUTO";
	}

	@PrePersist
	public void beforeInsert() {
		createAt = LocalDateTime.now();
		createBy = SecurityUtils.getLoginUtilisateur() != null ? SecurityUtils.getLoginUtilisateur() : "IMPORT_AUTO";
	}
}