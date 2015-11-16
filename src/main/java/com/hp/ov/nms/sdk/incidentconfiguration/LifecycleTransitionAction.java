package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class LifecycleTransitionAction {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private String command;
	private String commandType;
	private String lifeCycleState;


	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getLifeCycleState() {
		return lifeCycleState;
	}
	public void setLifeCycleState(String lifeCycleState) {
		this.lifeCycleState = lifeCycleState;
	}

}
