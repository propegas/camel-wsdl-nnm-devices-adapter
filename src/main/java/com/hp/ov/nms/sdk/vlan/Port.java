package com.hp.ov.nms.sdk.vlan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Port {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private String associatedInterface;
	private String hostedOn;
	private String id;
	private String name;
	private String uuid;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHostedOn() {
		return hostedOn;
	}
	public void setHostedOn(String hostedOn) {
		this.hostedOn = hostedOn;
	}
	public String getAssociatedInterface() {
		return associatedInterface;
	}
	public void setAssociatedInterface(String associatedInterface) {
		this.associatedInterface = associatedInterface;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}





}
