package com.hp.ov.nms.sdk.vlan;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.iface.Interface;


@Local
public interface NmsVLANLocal {
    VLAN[] getVLANs(Filter filter) throws NmsVLANException;
    Interface[] getInterfacesForVLAN(String vlanId) throws NmsVLANException;
    VLAN[] getVLANsForInterface(String interfaceId)throws NmsVLANException;
    Port[] getPortsForVLAN(String vlanId)throws NmsVLANException;
    Port[] getPortsForVLANbyId(String id)throws NmsVLANException;
    VLAN[] getVLANsForDevice(String deviceId)throws NmsVLANException;
}
