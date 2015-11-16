package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RemoteNNMEventConfig extends IncidentConfig {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String oId="";

	public String getOId() {
		return oId;
	}

	public void setOId(String id) {
		oId = id;
	}

}
