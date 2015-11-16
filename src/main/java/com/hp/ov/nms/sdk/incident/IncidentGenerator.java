/*
 * (C) Copyright 2005 Hewlett-Packard Development Company, L.P.
 *
 *  This software is the confidential and proprietary information of
 *  Hewlett-Packard. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Hewlett-Packard.
 *
 *  Hewlett-Packard makes no representations or warranties about the
 *  suitability of the software, either express or implied, including
 *  but not limited to the implied warranties of merchantability,
 *  fitness for a particular purpose, or non-infringement. Hewlett-Packard
 *  shall not be liable for any damages suffered by licensee as a result
 *  of using, modifying or distributing this software or its derivatives.
 *
 *  Created on Nov 7, 2006
 */
package com.hp.ov.nms.sdk.incident;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.jboss.ejb3.annotation.Management;
import org.jboss.ejb3.annotation.Service;

import com.hp.ov.nms.sdk.client.SampleClient;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * JMX-Console management bean for IncidentGenerator client for NmsIncident Service.
 *
 * @author Rocky
 *
 */

@Service(objectName = "com.hp.ov.nms.sdk.incident:mbean=IncidentGenerator")
@Management(IncidentGeneratorMBean.class)
public class IncidentGenerator extends SampleClient implements IncidentGeneratorMBean {
    private static final Logger LOGGER = Logger.getLogger(IncidentGenerator.class.getName());
    private String sourceNodeUuid;
    private String sourceNodeName;
    private String sourceUuid;
    private String sourceName;
    private String sourceType;
    private String nature;
    private boolean rcaActive;
    private String uuid;
    private String name;
    private String priority;

    /**
     * Inject an incident to NNM via web-services.
     * @throws NmsIncidentException
     */
    public void addIncident(String name) throws NmsIncidentException {
        IncidentMgmtEvent incident=new IncidentMgmtEvent();
        incident.setName(name);

        incident.setUuid(uuid);
        incident.setSourceType(sourceType);
        incident.setPriority(priority);
        incident.setSourceName(sourceName);
        incident.setSourceUuid(sourceUuid);
        incident.setSourceNodeName(sourceNodeName);
        incident.setSourceNodeUuid(sourceNodeUuid);
        incident.setNature((nature==null || nature.equals(""))?Nature.NONE:Nature.valueOf(nature));
        incident.setOriginOccurrenceTime(new Date(System.currentTimeMillis()));
        incident.setCias(new Cia[0]);

        NmsIncident incidentService=getIncidentService();
        incidentService.addIncident(incident);
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public boolean isRcaActive() {
        return rcaActive;
    }

    public void setRcaActive(boolean rcaActive) {
        this.rcaActive = rcaActive;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUuid() {
        return sourceUuid;
    }

    public void setSourceUuid(String sourceUuid) {
        this.sourceUuid = sourceUuid;
    }

    public String getSourceNodeName() {
        return sourceNodeName;
    }

    public void setSourceNodeName(String sourceNodeName) {
        this.sourceNodeName = sourceNodeName;
    }

    public String getSourceNodeUuid() {
        return sourceNodeUuid;
    }

    public void setSourceNodeUuid(String sourceNodeUuid) {
        this.sourceNodeUuid = sourceNodeUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
