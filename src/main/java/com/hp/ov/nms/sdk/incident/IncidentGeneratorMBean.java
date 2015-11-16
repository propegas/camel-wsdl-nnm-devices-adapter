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

import org.jboss.ejb3.annotation.Management;

import com.hp.ov.nms.sdk.client.SampleClientMBean;

/**
 * JMX-Console management interface for IncidentGenerator client for NmsIncident Service.
 *
 * @author Rocky
 *
 */
@Management
public interface IncidentGeneratorMBean extends SampleClientMBean {
    void addIncident(String name) throws NmsIncidentException;

    public String getNature();
    public void setNature(String nature);
    public boolean isRcaActive();
    public void setRcaActive(boolean rcaActive);
    public String getSourceType();
    public void setSourceType(String sourceType);
    public String getSourceName();
    public void setSourceName(String sourceName);
    public String getSourceUuid();
    public void setSourceUuid(String sourceUuid);
    public String getSourceNodeName();
    public void setSourceNodeName(String sourceNodeName);
    public String getSourceNodeUuid();
    public void setSourceNodeUuid(String sourceNodeUuid);
    public String getName();
    public void setName(String name);
    public String getPriority();
    public void setPriority(String priority);
    public String getUuid();
    public void setUuid(String uuid);
}
