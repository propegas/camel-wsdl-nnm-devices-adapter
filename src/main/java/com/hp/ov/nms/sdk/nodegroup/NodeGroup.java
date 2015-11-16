package com.hp.ov.nms.sdk.nodegroup;

import java.io.Serializable;
import java.util.Date;

import com.hp.ov.nms.sdk.inventory.Status;

/**
 * NodeGroup object for SDK.
 *
 * @author Rocky
 *
 */
public class NodeGroup implements Serializable {
	private String id;
	private String uuid;
	private String name;
	private Status status;
	private Date created;
	private Date modified;

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
