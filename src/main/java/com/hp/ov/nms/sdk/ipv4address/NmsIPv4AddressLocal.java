package com.hp.ov.nms.sdk.ipv4address;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.ManagementMode;

/**
 * @deprecated Use com.hp.ov.nms.sdk.ipaddress.NmsIPAddressLocal
 */
@Local
public interface NmsIPv4AddressLocal {
    IPv4Address[] getIPv4Addresses(Filter filter) throws NmsIPv4AddressException;
    AddressConclusion[] getConclusions(String id) throws NmsIPv4AddressException;
    void updateNotes(String id,String value) throws NmsIPv4AddressException;
    void updateManagementMode(String id,ManagementMode managementMode) throws NmsIPv4AddressException;
}
