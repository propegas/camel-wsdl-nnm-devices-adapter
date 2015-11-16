package com.hp.ov.nms.sdk.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserGroupMembershipDTO implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

	private static final long serialVersionUID = 1L;

	String accountName;
	String userGroupName;

	public UserGroupMembershipDTO() {

	}

	public UserGroupMembershipDTO(String accountName, String userGroupName) {
		super();
		this.accountName = accountName;
		this.userGroupName = userGroupName;
	}

	/**
	 * Get the name of the Account.
	 * @return the account name.
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Get the name of the UserGroup.
	 * @return the UserGroup name.
	 */
	public String getUserGroupName() {
		return userGroupName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	@Override
	public String toString() {
		return "UserGroupMembershipDTO [accountName=" + accountName
				+ ", userGroupName=" + userGroupName + "]";
	}


}
