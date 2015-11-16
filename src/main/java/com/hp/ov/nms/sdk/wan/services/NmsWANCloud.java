/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan.services;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.wan.WanCloud;

/**
 *
 * @author haswellj
 */
public interface NmsWANCloud {

  
  /**
   * Find all connections matched by the provided filter
   * @param filter the filter to use to search for connections
   * @return a non-null array of WANClouds.  If nothing is found, the array is empty
   * @throws NmsWANCloudException if there is any issue retrieving the connections
   */
  WanCloud[] getClouds(Filter filter) throws NmsWANCloudException;

  /**
   * count the number of connections matched by the specified filter
   * @param filter the filter to use to search for connections
   * @return a non-null integer. 
   * @throws NmsWANCloudException if there is any issue retrieving the count
   */
  Integer getCloudCount(Filter filter) throws NmsWANCloudException;


  /**
   * Add the specified CustomAttributes to the WANCloud with the specified ID. Any existing 
   * CustomAttributes with a name that's the same as one of the specified CustomAttribute names are
   * overwritten by the new value.
   * 
   * An exception is thrown if a connection with the specified ID is not found
   * 
   * @param id the id of the connection to add custom attributes to
   * @throws NmsWANConnectioNException if there is any issue adding the CustomAttributes to the connection
   * 
   */
  void addExtendedAttributes(String id, CustomAttribute[] customAttributes) throws NmsWANCloudException;


  /**
   * Remove all of the CustomAttributes with a value in the specified extendedAttributeNames.  If no such 
   * CustomAttribute name exists, the name is ignored.
   */
  void removeExtendedAttributes(String id, String[] extendedAttributeNames) throws NmsWANCloudException;


  /**
   * Add each of the specified CustomAttributes to each of the IDs provided
   * @param ids
   * @param customAttributes 
   */
  void addExtendedAttributes(String[] ids, CustomAttribute[] customAttributes) throws NmsWANCloudException;


  void removeExtendedAttributes(String[] ids, String[] customAttributeNames) throws NmsWANCloudException;
  
  
}
