package com.hp.ov.nms.sdk.iface;

import java.io.Serializable;
import java.util.Date;

/**
 * Public Conclusion class for SDK Node Conclusions.
 *
 * @author Rocky
 *
 */
public class InterfaceConclusion implements Serializable {
	String uuid;
	String incidentUuid;
	String status;
	String conclusion;
	Date timestamp;
	
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getIncidentUuid() {
		return incidentUuid;
	}
	public void setIncidentUuid(String incidentUuid) {
		this.incidentUuid = incidentUuid;
	}
}
