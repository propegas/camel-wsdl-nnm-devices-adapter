package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * SNMP configuration responses indicate how a device is currently configured.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SnmpConfigResponse extends SnmpConfig implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private SnmpConfigActive activeConfig; //Confirmed settings used by NNM (if known, null otherwise)
    private String hostname;
    private String nodeID;
    private boolean snmpSupported;
    private SnmpConfigV2 v2Config; //Configured v2 settings representing 'potential' settings
    private SnmpConfigV3[] v3Config; //Configured v3 settings representing 'potential' settings

    public SnmpConfigActive getActiveConfig() {
        return activeConfig;
    }
    public void setActiveConfig(SnmpConfigActive activeConfig) {
        this.activeConfig = activeConfig;
    }
    public SnmpConfigV2 getV2Config() {
        return v2Config;
    }
    public void setV2Config(SnmpConfigV2 v2Config) {
        this.v2Config = v2Config;
    }
    public SnmpConfigV3[] getV3Config() {
        return v3Config;
    }
    public void setV3Config(SnmpConfigV3[] v3Config) {
        this.v3Config = v3Config;
    }
    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public boolean isSnmpSupported() {
        return snmpSupported;
    }
    public void setSnmpSupported(boolean isSnmpSupported) {
        this.snmpSupported = isSnmpSupported;
    }
    public String getNodeID() {
        return nodeID;
    }
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }
}
