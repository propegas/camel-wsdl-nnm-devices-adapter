package com.hp.ov.nms.sdk.snmp;

import java.io.Serializable;

/**
 * SNMP configuration used to either 'set' NNM configuration or to 'override' NNM configuration for a specific SNMP request.
 *
 * @author Rocky
 *
 */
public class SnmpConfigOverrides extends SnmpConfig implements Serializable {
    private Integer snmpVersion;            //Only used for SNMP queries if version is known by client.
    private String v2ReadCommunityString;  //Ignored if null or if snmpVersion is known to equal 'V3' or if operation requires a writeCommunityString
    private String v2WriteCommunityString; //Ignored if null or if snmpVersion is known to equal 'V3' or if operation is read-only
    private SnmpConfigV3 v3Config;         //Ignored if null or if snmpVersion is known to equal 'V1' or 'V2'

    public Integer getSnmpVersion() {
        return snmpVersion;
    }
    public void setSnmpVersion(Integer snmpVersion) {
        this.snmpVersion = snmpVersion;
    }
    public String getV2ReadCommunityString() {
        return v2ReadCommunityString;
    }
    public void setV2ReadCommunityString(String readCommunityString) {
        v2ReadCommunityString = readCommunityString;
    }
    public String getV2WriteCommunityString() {
        return v2WriteCommunityString;
    }
    public void setV2WriteCommunityString(String writeCommunityString) {
        v2WriteCommunityString = writeCommunityString;
    }
    public SnmpConfigV3 getV3Config() {
        return v3Config;
    }
    public void setV3Config(SnmpConfigV3 config) {
        v3Config = config;
    }
}
