package com.hp.ov.nms.sdk.snmp;

import javax.ejb.Local;

@Local
public interface NmsSnmpLocal {
    SnmpConfigResponse[] getNodeConfigByIds(String[] nodeIds) throws NmsSnmpException;
	SnmpConfiguration[] getNodeConfiguration(String[] hostnames) throws NmsSnmpException;
    public void setNodeConfiguration(String[] hostnames, String[] readCommunityStrings, String managementAddress,
            Integer snmpPort, Integer retries, Integer timeout) throws NmsSnmpException;
    SnmpConfigResponse[] getNodeConfig(String[] hostnames) throws NmsSnmpException;
    void setNodeConfig(String[] hostnames, SnmpConfigOverrides config) throws NmsSnmpException;
    SnmpResponse[] snmpGet(String[] reqOids, String[] hostnames,  SnmpConfigOverrides config) throws NmsSnmpException;
    SnmpResponse[] snmpGetNext(String[] reqOids, String[] hostnames, SnmpConfigOverrides config) throws NmsSnmpException;
    SnmpResponse[] snmpGetBulk(String[] reqOids, String[] hostnames, SnmpConfigOverrides config,
            Integer nonRepeaters, Integer maxRepetitons) throws NmsSnmpException;
    SnmpResponse[] snmpSet(String setOidStr, String value, Asn1Constant asnType, String[] hostnames, SnmpConfigOverrides config) throws NmsSnmpException;
    String getNameForOid(String oid) throws NmsSnmpException;
    String getOidForName(String name) throws NmsSnmpException;
    String[] listDefaultReadCommunityStrings() throws NmsSnmpException;
    void addDefaultReadCommunityString(String commString) throws NmsSnmpException;
    void removeDefaultReadCommunityString(String commString) throws NmsSnmpException;
}
