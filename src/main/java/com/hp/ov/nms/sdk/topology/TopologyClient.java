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
package com.hp.ov.nms.sdk.topology;

import java.util.logging.Logger;

import org.jboss.ejb3.annotation.Management;
import org.jboss.ejb3.annotation.Service;

import com.hp.ov.nms.sdk.client.SampleClient;

/**
 * JMX-Console management bean for the Topology service.
 *
 * @author Jamie Sampey
 *
 */
@Service(objectName="com.hp.ov.nms.sdk.topology:mbean=TopologyClient")
@Management(TopologyClientMBean.class)
public class TopologyClient extends SampleClient implements TopologyClientMBean {
    private static final Logger log = Logger.getLogger(TopologyClient.class.getName());

    public String getPath(String startNode, String endNode) {
        NetworkPathResponse npr = null;
        try {
            NmsTopology service=getTopologyService();
            npr = service.getPath(startNode, endNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prettyPrint(npr);
    }

    private String prettyPrint(NetworkPathResponse npr) {
        StringBuilder sb = new StringBuilder();
        if (npr == null) return sb.toString();

        sb.append("Path:\n");
        sb.append("  From:\t" + npr.getPathSource() + "\n");
        sb.append("  To:\t" + npr.getPathDest() + "\n\n");

        NetworkPathElement[] pathElements = npr.getPathElements();
        if(pathElements!=null) {
            sb.append(pathElements.length + " elements in the path.\n\n");
            for(int i=0; i<pathElements.length; i++){
                sb.append("(" + (i+1) + ") ");
                sb.append(pathElements[i].toString() + "\n");
            }
        }
        return sb.toString();
    }
}
