package com.hp.ov.nms.sdk.incident;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsIncidentLocal {
    Incident[] getIncidents(Filter filter) throws NmsIncidentException;
    Integer getIncidentCount(Filter filter) throws NmsIncidentException;
    void addIncident(IncidentMgmtEvent incident) throws NmsIncidentException;
    boolean deleteIncident(String id) throws NmsIncidentException;
    boolean deleteIncidentByUuid(String uuid) throws NmsIncidentException;
    Incident[] getChildIncidents(String incidentId) throws NmsIncidentException;
    IncidentCorrelation[] getChildCorrelations(String incidentId) throws NmsIncidentException;
    String[] getAssignToPrincipals() throws NmsIncidentException;
    void updateAssignedTo(String id,String assignTo) throws NmsIncidentException;
    void updateNotes(String id,String value) throws NmsIncidentException;
    void updateLifecycleState(String id,String lifecycleState) throws NmsIncidentException;
    void updatePriority(String id,String priority) throws NmsIncidentException;
    void updateCias(String id,Cia[] cias) throws NmsIncidentException;
}
