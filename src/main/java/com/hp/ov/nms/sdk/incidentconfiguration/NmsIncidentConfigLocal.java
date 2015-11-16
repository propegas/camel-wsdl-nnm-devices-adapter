package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsIncidentConfigLocal {

    //IncidentConfig[] getConfigurations(Filter filter)throws NmsIncidentConfigException;
    RemoteNNMEventConfig[] getRemoteEventConfig(Filter filter) throws NmsIncidentConfigException;
    ManagementEventConfig[] getManagementEventConfig(Filter filter)throws NmsIncidentConfigException;
    SnmpTrapConfig[] getSnmpTrapConfig(Filter filter)throws NmsIncidentConfigException;
    PairwiseConfig[] getPairwiseConfig(Filter filter)throws NmsIncidentConfigException;

}
