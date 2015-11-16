package com.hp.ov.nms.sdk.vlan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class VLAN {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private String id;
	private String name;
	private String uuid;
	private String vlanId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVlanId() {
		return vlanId;
	}
	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean equals(final Object o){

		if(this == o)
			return true;

		if(o==null || !(o instanceof VLAN))
			return false;

		VLAN vlan=(VLAN)o;

		if(id.equals(vlan.getId()))
			return true;

		return false;

	}

	public int hashCode(){

		return uuid.hashCode();

	}


}
