package ru.atc.camel.nnm.devices.api;

public class Feed2 {

    private String serverName;
    private String serverIp;
	
	@Override
	public String toString() {
		return serverName + serverIp;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
}