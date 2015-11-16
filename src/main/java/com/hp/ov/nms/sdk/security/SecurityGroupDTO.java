package com.hp.ov.nms.sdk.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityGroupDTO implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

	private static final long serialVersionUID = 1L;

	private String name;
	private String uuid;

	public SecurityGroupDTO() {

	}

	public SecurityGroupDTO(String name, String uuid) {
		super();
		this.name = name;
		this.uuid = uuid;
	}

	/**
	 * Get the name of the SecurityGroup.
	 * @return the SecurityGroup name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the UUID string for this SecurityGroup.
	 * @return the SecurityGroup UUID string.
	 */
	public String getUuid() {
		return uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "SecurityGroupDTO [name=" + name + ", uuid=" + uuid + "]";
	}

}
