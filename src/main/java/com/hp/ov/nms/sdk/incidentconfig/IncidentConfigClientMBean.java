package com.hp.ov.nms.sdk.incidentconfig;

import org.jboss.ejb3.annotation.Management;

import com.hp.ov.nms.sdk.client.SampleClientMBean;

@Management
public interface IncidentConfigClientMBean extends SampleClientMBean {
    String listManagementEventIncidentConfig();
    String listRemoteEventIncidentConfig();
    String listSnmpTrapIncidentConfig();
    String listPairwiseIncidentConfig();
}
