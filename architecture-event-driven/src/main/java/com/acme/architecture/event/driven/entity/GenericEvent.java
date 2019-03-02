package com.acme.architecture.event.driven.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.acme.architecture.common.entity.AbstractEntity;

public class GenericEvent extends AbstractEntity {
	
	private static final long serialVersionUID = -7644255835286636163L;

	private String id;
	
	private String parentId;
	
	private String name;
	
	private String type; 
     
	private String author;
	
	private Long expirationSeconds;
	
	private String payload;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private Date deletedDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getExpirationSeconds() {
		return expirationSeconds;
	}

	public void setExpirationSeconds(Long expirationSeconds) {
		this.expirationSeconds = expirationSeconds;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getParentId()).append(getName()).append(getType()).append(getAuthor()).append(getExpirationSeconds()).append(getPayload()).append(getCreatedDate()).append(getUpdatedDate()).append(getDeletedDate()).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GenericEvent)) {
			return false;
		}
		
		final GenericEvent other = (GenericEvent)object;

		return new EqualsBuilder().append(getId(), other.getId())
				.append(getParentId(), other.getParentId())
				.append(getName(), other.getName())
				.append(getType(), other.getType())
				.append(getAuthor(), other.getAuthor())
				.append(getExpirationSeconds(), other.getExpirationSeconds())
				.append(getPayload(), other.getPayload())
				.append(getCreatedDate(), other.getCreatedDate())
				.append(getUpdatedDate(), other.getUpdatedDate())
				.append(getDeletedDate(), other.getDeletedDate())
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId())
				.append("parentId", getParentId())
				.append("name", getName())
				.append("type", getType())
				.append("author", getAuthor())
				.append("expiration", getExpirationSeconds())
				.append("payload", getPayload())
				.append("created", getCreatedDate())
				.append("updated", getUpdatedDate())
				.append("deleted", getDeletedDate())
				.toString();
	}

}

