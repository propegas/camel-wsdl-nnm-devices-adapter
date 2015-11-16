package ru.atc.camel.nnm.devices.api;

public class RESTNetworkAdvisorDevice {

    private String id;
    private String name;
   // private String wwn;
    private String operationalStatus;
    private String state;
    private String model;
    private String uuid;
    private String longName;
    private String systemName;
    private String deviceCategory;
    
    private String[] groups;

	
	
	@Override
	public String toString() {
		return id + " " + name;
	}


	public String getKey() {
		return id;
	}


	public void setKey(String key) {
		this.id = key;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOperationalStatus() {
		return operationalStatus;
	}


	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getLongName() {
		return longName;
	}


	public void setLongName(String longName) {
		this.longName = longName;
	}


	public String getSystemName() {
		return systemName;
	}


	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}


	public String getDeviceCategory() {
		return deviceCategory;
	}


	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}


	public String[] getGroups() {
		return groups;
	}


	public void setGroups(String[] groups) {
		this.groups = groups;
	}
}