package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * SNMP base configuration used as basis for all SNMP Configuration requests, responses, and overrides.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SnmpConfig implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String managementAddress;
    private Integer msTimeout;
    private Integer retries;
    private Integer snmpPort;
    private String snmpProxyAddress;
    private Integer snmpProxyPort;
    private Boolean snmpProxyTargetVarbindEnabled;

    public String getManagementAddress() {
        return managementAddress;
    }
    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
    }
    public Integer getMsTimeout() {
        return msTimeout;
    }
    public void setMsTimeout(Integer msTimeout) {
        this.msTimeout = msTimeout;
    }
    public Integer getRetries() {
        return retries;
    }
    public void setRetries(Integer retries) {
        this.retries = retries;
    }
    public Integer getSnmpPort() {
        return snmpPort;
    }
    public void setSnmpPort(Integer snmpPort) {
        this.snmpPort = snmpPort;
    }
    public String getSnmpProxyAddress() {
        return snmpProxyAddress;
    }
    public void setSnmpProxyAddress(String snmpProxyAddress) {
        this.snmpProxyAddress = snmpProxyAddress;
    }
    public Integer getSnmpProxyPort() {
        return snmpProxyPort;
    }
    public void setSnmpProxyPort(Integer snmpProxyPort) {
        this.snmpProxyPort = snmpProxyPort;
    }
    public Boolean isSnmpProxyTargetVarbindEnabled() {
        return snmpProxyTargetVarbindEnabled;
    }
    public void setSnmpProxyTargetVarbindEnabled(Boolean snmpProxyTargetVarbindEnabled) {
        this.snmpProxyTargetVarbindEnabled = snmpProxyTargetVarbindEnabled;
    }
}
