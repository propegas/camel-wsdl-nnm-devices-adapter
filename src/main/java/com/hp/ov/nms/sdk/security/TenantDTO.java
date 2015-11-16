package com.hp.ov.nms.sdk.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TenantDTO implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

	private static final long serialVersionUID = 1L;

    private String group;
    private String name;
    private String uuid;

	public TenantDTO() {

	}

	public TenantDTO(String name, String uuid, String group) {
		super();
		this.name = name;
		this.uuid = uuid;
		this.group = group;
	}

    /**
     * Get the name of the Tenant.
     * @return the name of the Tenant.
     */
	public String getName() {
		return name;
	}
	/**
	 * Get the UUID string for the Tenant.
	 * @return the Tenant UUID string.
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * Get the UUID string for the Security group associated with this Tenant.
	 * @return the SecurityGroup UUID string.
	 */
	public String getSecurityGroupUuid() {
		return group;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setSecurityGroupUuid(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "TenantDTO [name=" + name + ", uuid=" + uuid + ", securityGroupUuid="
				+ group + "]";
	}

}
