package com.hp.ov.nms.sdk.ipsubnet;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsIPSubnetLocal {
    IPSubnet[] getIPSubnets(Filter filter) throws NmsIPSubnetException;
    String[] getTenantsByUuids(String[] uuids) throws NmsIPSubnetException;
    void updateNotes(String id,String value) throws NmsIPSubnetException;
}
