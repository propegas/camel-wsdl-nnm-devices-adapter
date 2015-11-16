package com.hp.ov.nms.sdk.ipaddress;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.ManagementMode;

@Local
public interface NmsIPAddressLocal {
    IPAddress[] getIPAddresses(Filter filter) throws NmsIPAddressException;
    IPAddressConclusion[] getConclusions(String id) throws NmsIPAddressException;
    String[] getTenantsByUuids(String[] uuids) throws NmsIPAddressException;
    String[] getMappedAddressesByUuids(String[] uuids) throws NmsIPAddressException;
    void updateNotes(String id,String value) throws NmsIPAddressException;
    void updateManagementMode(String id,ManagementMode managementMode) throws NmsIPAddressException;
    void addCapabilities(String id,Capability[] capabilities) throws NmsIPAddressException;
    String[] getArpCacheIPAddresses(Filter filter) throws NmsIPAddressException;
}
