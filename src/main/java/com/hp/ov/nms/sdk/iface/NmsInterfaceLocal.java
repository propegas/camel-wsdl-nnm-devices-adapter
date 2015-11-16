package com.hp.ov.nms.sdk.iface;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.inventory.ManagementMode;

@Local
public interface NmsInterfaceLocal {
    Interface[] getInterfaces(Filter filter) throws NmsInterfaceException;
    Integer getInterfaceCount(Filter filter) throws NmsInterfaceException;
    void addCapabilities(String id,Capability[] capabilities) throws NmsInterfaceException;
    void removeCapabilities(String id,String[] capabilities) throws NmsInterfaceException;
    String[] addCapabilitiesBulk(String[] nodeIds,Capability[] capabilities) throws NmsInterfaceException;
    String[] removeCapabilitiesBulk(String[] nodeIds,String[] capabilityKeys) throws NmsInterfaceException;
    void updateCustomAttributes(String id,CustomAttribute[] customAttributes) throws NmsInterfaceException;
    InterfaceConclusion[] getConclusions(String id) throws NmsInterfaceException;
    void updateNotes(String id,String value) throws NmsInterfaceException;
    void updateManagementMode(String id,ManagementMode managementMode) throws NmsInterfaceException;
    /**
     * Add a list of custom attributes to an interface
     * @param id Interface PersistenceID
     * @param customAttributes A list of custom attributes to add
     * @throws NmsInterfaceException
     */
    void addCustomAttributes(String id,CustomAttribute[] customAttributes) throws NmsInterfaceException;

    /**
     * Add a bulk set of custom attributes with the same name to a set of interfaces with corresponding values.
     * The order of interface ID and custom attribute value matches in the array.
     * @param caName The name of the custom attribute
     * @param ifaceIds An array of interface IDs
     * @param caValues An array of custom attribute values corresponding to the interface IDs
     * @return # of custom attributes successfully added
     */
    int addBulkCustomAttributes(final String caName, final String[] ifaceIds, final String[] caValues);
    public String[] addCustomAttributesBulk(String[] ifaceIds,CustomAttribute[] customAttributes) throws NmsInterfaceException;

    /**
     * Remove a list of custom attributes from an interface
     * @param id Interface persistence ID
     * @param customAttributes list of custom attribute names
     * @throws NmsInterfaceException
     */
    void removeCustomAttributes(String id,String[] customAttributes) throws NmsInterfaceException;
    String[] removeCustomAttributesBulk(String[] ifaceIds,String[] customAttributes) throws NmsInterfaceException;
    Long[] getInputSpeedBulk(String[] ifaceIds) throws NmsInterfaceException;
    Long[] getOutputSpeedBulk(String[] ifaceIds) throws NmsInterfaceException;
}
