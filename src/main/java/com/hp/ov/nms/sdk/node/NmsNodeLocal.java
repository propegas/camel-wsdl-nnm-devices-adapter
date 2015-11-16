package com.hp.ov.nms.sdk.node;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.inventory.ManagementMode;
import com.hp.ov.nms.sdk.inventory.Status;

@Local
public interface NmsNodeLocal {
    Node[] getNodes(Filter filter) throws NmsNodeException;
    Integer getNodeCount(Filter filter) throws NmsNodeException;
    NodeConclusion[] getConclusions(String id) throws NmsNodeException;
    void updateNotes(String id,String value) throws NmsNodeException;
    String[] addCapabilitiesBulk(String[] nodeIds,Capability[] capabilities) throws NmsNodeException;
    String[] removeCapabilitiesBulk(String[] nodeIds,String[] capabilityKeys) throws NmsNodeException;
    void addCapabilities(String id,Capability[] capabilities) throws NmsNodeException;
    void removeCapabilities(String id,String[] capabilityKeys) throws NmsNodeException;
    void updateCustomAttributes(String id,CustomAttribute[] customAttributes) throws NmsNodeException;
    void updateManagementMode(String id,ManagementMode managementMode) throws NmsNodeException;
    boolean deleteNode(String id) throws NmsNodeException;
    boolean deleteNodeByUuid(String uuid) throws NmsNodeException;
    void addSeeds(String[] hostnamesOrIPs) throws NmsNodeException;
    void addSeedsForTenant(String[] hostnamesOrIPs,String tenentName,String notes) throws NmsNodeException;
    void removeSeeds(String[] hostnamesOrIPs) throws NmsNodeException;
    void pollHosts(String[] hostnamesOrIPs) throws NmsNodeException;
    void rediscoverHosts(String[] hostnamesOrIPs) throws NmsNodeException;
    void addConclusion(String nodeUUID,String incidentId,Status status,String conclusion, String[] cancelledConclusions)throws NmsNodeException;
    void addHints(String[] hosts)throws NmsNodeException;

    /**
     * Get the NNM system name for the given node.
     *
     * @param nodeId either a Node PersistenceID or UUID (both are supported)
     * @return the NNM system name for the node
     * @throws NmsNodeException
     */
    String getNnmSystemName(final String nodeId) throws NmsNodeException;

    /**
     * Is this a local node?
     *
     * @param nodeId either a Node PersistenceID or UUID (both are supported)
     * @return true if the given Node is local to this station,
     * false otherwise
     * @throws NmsNodeException
     */
    boolean isLocal(final String nodeId) throws NmsNodeException;

    /**
     * Is the given Node to be forwarded from the regional manager to the global manager?
     * That is, does the given node pass the geo node export filter?
     *
     * @param nodeId either a Node PersistenceID or UUID (both are supported)
     * @return true if this node is forwardable
     * @throws NmsNodeException
     */
    boolean isForwardable(final String nodeId) throws NmsNodeException;

    /**
     * Return the given Nodes that are forwardable from regional to global stations.
     * That is, return the given Nodes that pass the geo node export filter.
     *
     * @param nodeIds Node PersistenceID or UUID (both are supported and all elements must be the same type)
     * @return Node PersistenceID or UUID (both are supported) that pass the geo export filter
     * @throws NmsNodeException
     */
    String[] getForwardableNodes(final String[] nodeIds) throws NmsNodeException;

    /**
     * Add a list of custom attributes to a node
     * @param id Node PersistenceID
     * @param customAttributes A list of custom attributes to add
     * @throws NmsNodeException
     */
    public void addCustomAttributes(String id,CustomAttribute[] customAttributes) throws NmsNodeException;
    public String[] addCustomAttributesBulk(String[] nodeIds,CustomAttribute[] customAttributes) throws NmsNodeException;

    /**
     * Remove a list of custom attributes from a node
     * @param id Node persistence ID
     * @param customAttributes list of custom attribute names
     * @throws NmsNodeException
     */
    public void removeCustomAttributes(String id,String[] customAttributes) throws NmsNodeException;
    public String[] removeCustomAttributesBulk(String[] nodeIds,String[] customAttributes) throws NmsNodeException;
}
