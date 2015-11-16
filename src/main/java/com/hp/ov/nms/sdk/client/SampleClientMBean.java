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
package com.hp.ov.nms.sdk.client;

/**
 * Sample MBean basic interface.
 *
 * @author Rocky
 *
 */
public interface SampleClientMBean {
    boolean isSsl();
    void setSsl(boolean ssl);
    String getHost();
    void setHost(String host);
    int getPort();
    void setPort(int port);
    String getNnmUser();
    void setNnmUser(String user);
    String getNnmPass();
    void setNnmPass(String password);
    String getWsdlUrl();
    boolean isNnmConnectionOk();
    Integer getSysUptime();
}
