package com.hp.ov.nms.sdk.nodegroup;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsNodeGroupLocal {
	NodeGroup[] getNodeGroups(Filter filter) throws NmsNodeGroupException;
	/**
	 * Get all node groups this node belongs to
	 * @param nodeId Node Persistence ID
	 * @return List of node groups
	 * @throws NmsNodeGroupException 
	 */
	NodeGroup[] getNodeGroupsByNode(final String nodeId) throws NmsNodeGroupException;
	
	String[] getMemberIds(String id) throws NmsNodeGroupException;
    NodeGroupConclusion[] getConclusions(String id) throws NmsNodeGroupException;
}
