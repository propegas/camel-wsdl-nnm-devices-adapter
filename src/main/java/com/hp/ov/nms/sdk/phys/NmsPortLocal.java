package com.hp.ov.nms.sdk.phys;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.Capability;

@Local
public interface NmsPortLocal {
	/**
	 * Get all filtered ports
	 * @param filter
	 * @return
	 */
	Port[] getPorts(final Filter filter) throws NmsSdkException;

	/**
	 * Add a set of capabilities
	 * @param id
	 * @param capabilities
	 * @throws NmsSdkException
	 */
	void addCapabilities(String id,Capability[] capabilities) throws NmsSdkException;

	/**
	 * Remove a set of capabilities from port
	 * @param id
	 * @param capabilities
	 * @throws NmsSdkException
	 */
	void removeCapabilities(String id,String[] capabilities) throws NmsSdkException;
}
