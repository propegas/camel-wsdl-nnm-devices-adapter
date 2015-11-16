package com.hp.ov.nms.sdk.snmp;


/**
 * SNMP configuration responses indicate how a device is currently configured.
 *
 * @author Rocky
 *
 */
public class SnmpConfigActive {
    private String managementAddress;
    private String snmpVersion;
    private String v2ReadCommunityString;
    private SnmpConfigV3 v3Config;

    public String getManagementAddress() {
        return managementAddress;
    }
    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
    }
    public String getSnmpVersion() {
        return snmpVersion;
    }
    public void setSnmpVersion(String snmpVersion) {
        this.snmpVersion = snmpVersion;
    }
    public String getV2ReadCommunityString() {
        return v2ReadCommunityString;
    }
    public void setV2ReadCommunityString(String readCommunityString) {
        v2ReadCommunityString = readCommunityString;
    }
    public SnmpConfigV3 getV3Config() {
        return v3Config;
    }
    public void setV3Config(SnmpConfigV3 config) {
        v3Config = config;
    }
}
