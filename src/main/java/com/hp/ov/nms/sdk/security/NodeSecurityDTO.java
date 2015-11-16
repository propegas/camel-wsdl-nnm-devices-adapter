package com.hp.ov.nms.sdk.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class NodeSecurityDTO implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

	private static final long serialVersionUID = 1L;

	private String nodeName;
	private String nodeUuid;
	private String securityGroupName;
	private String securityGroupUuid;
	private String tenantName;
	private String tenantUuid;

	public NodeSecurityDTO() {

	}

	public NodeSecurityDTO(String nodeName, String nodeUuid,
			String securityGroupName, String securityGroupUuid,
			String tenantName, String tenantUuid) {
		super();
		this.nodeName = nodeName;
		this.nodeUuid = nodeUuid;
		this.securityGroupName = securityGroupName;
		this.securityGroupUuid = securityGroupUuid;
		this.tenantName = tenantName;
		this.tenantUuid = tenantUuid;
	}
	/**
	 * Get the node name.
	 * @return the name of the Node.
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * Get the UUID string for the Node.
	 * @return the Node UUID string.
	 */
	public String getNodeUuid() {
		return nodeUuid;
	}
	/**
	 * Get the name of the SecurityGroup.
	 * @return the SecurityGroup name.
	 */
	public String getSecurityGroupName() {
		return securityGroupName;
	}
	/**
	 * Get the UUID string for the SecurityGroup associated with this node.
	 * @return the SecurityGroup UUID string.
	 */
	public String getSecurityGroupUuid() {
		return securityGroupUuid;
	}
	/**
	 * Get the name of the Tenant.
	 * @return the Tenant name.
	 */
	public String getTenantName() {
		return tenantName;
	}
	/**
	 * Get the UUID string for the Tenant associated with this node.
	 * @return the Tenant UUID string.
	 */
	public String getTenantUuid() {
		return tenantUuid;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setNodeUuid(String nodeUuid) {
		this.nodeUuid = nodeUuid;
	}

	public void setSecurityGroupName(String securityGroupName) {
		this.securityGroupName = securityGroupName;
	}

	public void setSecurityGroupUuid(String securityGroupUuid) {
		this.securityGroupUuid = securityGroupUuid;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public void setTenantUuid(String tenantUuid) {
		this.tenantUuid = tenantUuid;
	}

	@Override
	public String toString() {
		return "NodeSecurityDTO [nodeName=" + nodeName + ", nodeUuid="
				+ nodeUuid + ", securityGroupName=" + securityGroupName
				+ ", securityGroupUuid=" + securityGroupUuid + ", tenantName="
				+ tenantName + ", tenantUuid=" + tenantUuid + "]";
	}

}
