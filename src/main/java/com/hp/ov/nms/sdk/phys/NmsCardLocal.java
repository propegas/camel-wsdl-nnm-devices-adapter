package com.hp.ov.nms.sdk.phys;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.incident.IncidentConclusion;
import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.ManagementMode;
import com.hp.ov.nms.sdk.inventory.Status;

@Local
public interface NmsCardLocal {
	/**
	 * Get all filtered cards
	 * @param filter
	 * @return
	 */
	Card[] getCards(final Filter filter) throws NmsSdkException;

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

	/**
	 * Get all incident conclusions associated with a card
	 * @param cardPid Card persistence ID
	 * @return
	 * @throws NmsSdkException
	 */
    IncidentConclusion[] getConclusions(String cardPid) throws NmsSdkException;

    /**
     * Add an incident conclusion
     * @param cardPid Card persistence ID
     * @param incidentId Incident persistence ID
     * @param status
     * @param conclusion
     * @param cancelledConclusions
     * @throws NmsSdkException
     */
    void addConclusion(String cardPid,String incidentId,Status status,String conclusion, String[] cancelledConclusions)throws NmsSdkException;


    /**
     * Update management mode of a card
     * @param cardPid Card persistence ID
     * @param managementMode
     * @throws NmsSdkException
     */
    void updateManagementMode(String cardPid,ManagementMode managementMode) throws NmsSdkException;
}
