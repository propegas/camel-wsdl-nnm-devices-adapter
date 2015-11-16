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
package com.hp.ov.nms.sdk.inventory;

import java.util.List;

import org.jboss.ejb3.annotation.Management;

import com.hp.ov.nms.sdk.client.SampleClientMBean;
import com.hp.ov.nms.sdk.iface.NmsInterfaceException;
import com.hp.ov.nms.sdk.node.NmsNodeException;
import com.hp.ov.nms.sdk.node.Node;

/**
 * JMX-Console management interface for Inventory management bean.
 */
@Management
public interface InventoryClientMBean extends SampleClientMBean {
    int getCount();
    String pageThroughIncidents() throws Exception;
    String listIncidents();
    Integer getIncidentCount();
    Integer getNodeCount();
    Integer getInterfaceCount();
    String listMIBs();
    String listNodes();
    String listNodeGroups();
    String listInterfaces();
    String listIPAddresses();
    String listIPSubnets();
    String listL2Connections();
    Node getNodeByName(String nodeName) throws NmsNodeException;
    String getNodeConclusions(String id);
    String getNodeGroupConclusions(String id);
    String getInterfaceConclusions(String id);
    String getIPAddressConclusions(String id);
    String getNodeGroupMemberIds(String id);
    String getAnObjectUuid(String objectName);
    String getObjectIdFromUuid(String objectName,String uuid);
    String getObjectAttribute(String objectName,String attributeName,String id);
    String getChildIncidents(String incidentId);
    String listIncidentAssignToPrincipals();
    void updateIncidentAssignedTo(String id,String name);
    void updateIncidentNotes(String id,String notes);
    void addNodeCapabilities(String id,String capabilities);
    void removeNodeCapabilities(String id,String capabilities);
    void updateNodeCustomAttributes(String id, String attributeNames, String attributeValues);
    void updateNodeNotes(String id,String notes);
    void updateNodeManagementMode(String id,String mode);
    void updateIncidentLifecycleState(String id,String lifecycle);
    void updateIncidentPriority(String id,String priority);
    void updateIncidentCias(String id, String attributeNames, String attributeValues);
    void addInterfaceCapabilities(String id,String capabilities);
    void addIPAddressCapabilities(String id,String capabilities);
    void removeInterfaceCapabilities(String id,String capabilities);
    void updateInterfaceCustomAttributes(String id, String attributeNames, String attributeValues);
    void updateInterfaceNotes(String id,String notes);
    void updateInterfaceManagementMode(String id,String mode);
    void updateIPAddressNotes(String id,String notes);
    void updateIPAddressManagementMode(String id,String mode);
    void updateIPSubnetNotes(String id,String notes);
    void updateL2ConnectionNotes(String id,String notes);
    Boolean deleteNode(String id);
    Boolean deleteNodeByUuid(String uuid);
    Boolean deleteIncident(String id);
    Boolean deleteIncidentByUuid(String uuid);
    void selectFilter(Integer filterIndex);
    void addSeedsForDefaultTenant(String hostnames);
    void addSeeds(String hostnames,String tenentName,String notes);
    void removeSeeds(String hostnames);
    void rediscoverHosts(String hostnames);
    void pollHosts(String hostnames);
    void addConclusionsToNode(String nodeId,String incidentId, String status, String conclusion, String canceledConclusions);
    void addHints(String hostnames);
    String listVLANs();
    String getInterfacesForVLAN(String vlanId);
    String getVLANsForInterface(String interfaceId);
    String getPortsForVLAN(String vlanId);
    String getPortsForVLANbyId(String id);
    String getVLANsForDevice(String deviceId);

    /**
     * Get the NNM system name for the given node.
     *
     * @param nodeId either a Node PersistenceID or UUID (both are supported)
     * @return the NNM system name for the node
     * @throws NmsNodeException
     */
    String getNnmSystemName(final String nodeId);

    /**
     * Is the node local to this station?
     *
     * @param nodeId either a Node PersistenceID or UUID (both are supported)
     * @return true if the given Node is local to this station,
     * false otherwise
     */
    Boolean isLocal(String nodeId);

    /**
     * Is the given Node to be forwarded from the regional manager to the global manager?
     * That is, does the given node pass the geo node export filter?
     *
     * @param nodeId either a Node PersistenceID or UUID (both are supported)
     * @return true if this node is forwardable
     */
    Boolean isForwardable(String nodeId);

    /**
     * Return the given Nodes that are forwardable from regional to global stations.
     * That is, return the given Nodes that pass the geo node export filter.
     *
     * @param nodeIds Node PersistenceID or UUID (both are supported and all elements must be the same type)
     * @return Node PersistenceID or UUID (both are supported) that pass the geo export filter
     */
    List<String> getForwardableNodes(String[] nodeIds);

    /**
     * Get the source value of the L2 connection
     * @param linkId Persistence ID of the link
     * @return
     */
    String getL2ConnectionSource(final String linkId);

    /**
     * Add a new custom attribute to the node
     * @param nodeName Name of the node
     * @param name name of the custom attribute
     * @param value value of the custom attribute
     * @throws NmsNodeException
     */
    void addNodeCustomAttribute(String nodeName, String name, String value) throws NmsNodeException;

    /**
     * Remove a custom attribute from the node
     * @param nodeName Name of the node
     * @param name Name of the custom attribute to remove
     * @throws NmsNodeException
     */
    void removeNodeCustomAttribute(String nodeName, String name) throws NmsNodeException;

    /**
     * Add a new custom attribute to the interface
     * @param ifacePid Interface PID
     * @param name name of a custom attribute
     * @param value value of a custom attribute
     * @throws NmsInterfaceException
     */
    void addIfaceCustomAttribute(String ifacePid, String name, String value) throws NmsInterfaceException;

    /**
     * Remove a set of custom attributes from the interface
     * @param ifacePid Interface PID
     * @param names A list of names of custom attributes to remove
     * @throws NmsInterfaceException
     */
    void removeIfaceCustomAttribute(String ifacePid, String name) throws NmsInterfaceException;
    String addNodeCapabilitiesBulk(String nodeIds,String capabilityKeys);
    String removeNodeCapabilitiesBulk(String nodeIds,String capabilities);
    String addNodeCustomAttributesBulk(String nodeIds, String name, String value) throws NmsNodeException;
    String removeNodeCustomAttributesBulk(String nodeIds, String key) throws NmsNodeException;
    String addInterfaceCapabilitiesBulk(String ifaceIds,String capabilityKeys);
    String removeInterfaceCapabilitiesBulk(String ifaceIds,String capabilities);
    String addIfaceCustomAttributesBulk(String ifaceIds, String name, String value) throws NmsInterfaceException;
    String removeIfaceCustomAttributesBulk(String ifaceIds, String keys) throws NmsInterfaceException;
}
