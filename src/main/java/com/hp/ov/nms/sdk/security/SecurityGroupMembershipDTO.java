package com.hp.ov.nms.sdk.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityGroupMembershipDTO implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

	private static final long serialVersionUID = 1L;

	private String roleKey;
	private String roleValue;
	private String securityGroupUuid;
	private String userGroupName;

	public SecurityGroupMembershipDTO() {

	}

	public SecurityGroupMembershipDTO(String userGroupName,
			String securityGroupUuid, String roleKey, String roleValue) {
		super();
		this.userGroupName = userGroupName;
		this.securityGroupUuid = securityGroupUuid;
		this.roleKey = roleKey;
		this.roleValue = roleValue;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public String getSecurityGroupUuid() {
		return securityGroupUuid;
	}

	public String getRoleName() {
		return roleKey;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public void setSecurityGroupUuid(String securityGroupUuid) {
		this.securityGroupUuid = securityGroupUuid;
	}

	public void setRoleName(String roleKey) {
		this.roleKey = roleKey;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	@Override
	public String toString() {
		return "SecurityGroupMembershipDTO [userGroupName=" + userGroupName
				+ ", securityGroupUuid=" + securityGroupUuid + ", roleName="
				+ roleKey + ", roleValue=" + roleValue + "]";
	}



}
