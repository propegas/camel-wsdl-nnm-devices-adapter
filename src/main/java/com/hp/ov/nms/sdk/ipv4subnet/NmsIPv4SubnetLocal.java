package com.hp.ov.nms.sdk.ipv4subnet;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

/**
 * @deprecated Use com.hp.ov.nms.sdk.ipsubnet.NmsIPSubnetLocal
 */
@Local
public interface NmsIPv4SubnetLocal {
    IPv4Subnet[] getIPv4Subnets(Filter filter) throws NmsIPv4SubnetException;
    void updateNotes(String id,String value) throws NmsIPv4SubnetException;
}
