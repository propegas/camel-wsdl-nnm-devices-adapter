package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Public Incident class for SNMP SDK.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SnmpConfiguration implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String activeManagementAddress;
    private String activeReadCommunityString;
    private String managementAddress;
    private String nodeID;
    private String[] readCommunityStrings;
    private int retries;
    private int snmpPort;
    private boolean snmpSupported;
    private String snmpVersion;
    private int timeout;

    public String getActiveManagementAddress() {
        return activeManagementAddress;
    }
    public void setActiveManagementAddress(String activeManagementAddress) {
        this.activeManagementAddress = activeManagementAddress;
    }
    public String getActiveReadCommunityString() {
        return activeReadCommunityString;
    }
    public void setActiveReadCommunityString(String activeReadCommunityString) {
        this.activeReadCommunityString = activeReadCommunityString;
    }
    public boolean isSnmpSupported() {
        return snmpSupported;
    }
    public void setSnmpSupported(boolean isSnmpSupported) {
        this.snmpSupported = isSnmpSupported;
    }
    public String getManagementAddress() {
        return managementAddress;
    }
    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
    }
    public String getNodeID() {
        return nodeID;
    }
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }
    public String[] getReadCommunityStrings() {
        return readCommunityStrings;
    }
    public void setReadCommunityStrings(String[] readCommunityStrings) {
        this.readCommunityStrings = readCommunityStrings;
    }
    public int getRetries() {
        return retries;
    }
    public void setRetries(int retries) {
        this.retries = retries;
    }
    public int getSnmpPort() {
        return snmpPort;
    }
    public void setSnmpPort(int snmpPort) {
        this.snmpPort = snmpPort;
    }
    public String getSnmpVersion() {
        return snmpVersion;
    }
    public void setSnmpVersion(String snmpVersion) {
        this.snmpVersion = snmpVersion;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
