package com.hp.ov.nms.sdk.incidentconfiguration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseIncidentConfig implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private String enable;
	private String id;
	private String incidentConfigType;
	private String name;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIncidentConfigType() {
		return incidentConfigType;
	}
	public void setIncidentConfigType(String incidentConfigType) {
		this.incidentConfigType = incidentConfigType;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}




}
