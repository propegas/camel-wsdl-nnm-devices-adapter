package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ActionConfig {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private LifecycleTransitionAction[] actions;
	private String enable;

	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public LifecycleTransitionAction[] getActions() {
		return actions;
	}
	public void setActions(LifecycleTransitionAction[] actions) {
		this.actions = actions;
	}

}
