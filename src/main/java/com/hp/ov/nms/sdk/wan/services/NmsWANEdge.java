/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan.services;

import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.wan.WanEdge;

/**
 *
 * @author haswellj
 */
public interface NmsWANEdge {
  

  /**
   * Find all connections matched by the provided filter
   * @param filter the filter to use to search for connections
   * @return a non-null array of WANEdges.  If nothing is found, the array is empty
   * @throws NmsWANEdgeException if there is any issue retrieving the connections
   */
  WanEdge[] getEdges(Filter filter) throws NmsWANEdgeException;

  /**
   * count the number of connections matched by the specified filter
   * @param filter the filter to use to search for connections
   * @return a non-null integer. 
   * @throws NmsWANEdgeException if there is any issue retrieving the count
   */
  Integer getEdgeCount(Filter filter) throws NmsWANEdgeException;


  /**
   * Add the specified CustomAttributes to the WANEdge with the specified ID. Any existing 
   * CustomAttributes with a name that's the same as one of the specified CustomAttribute names are
   * overwritten by the new value.
   * 
   * An exception is thrown if a connection with the specified ID is not found
   * 
   * @param id the id of the connection to add custom attributes to
   * @throws NmsWANConnectioNException if there is any issue adding the CustomAttributes to the connection
   * 
   */
  void addExtendedAttributes(String id, CustomAttribute[] customAttributes) throws NmsWANEdgeException;


  /**
   * Remove all of the CustomAttributes with a value in the specified extendedAttributeNames.  If no such 
   * CustomAttribute name exists, the name is ignored.
   */
  void removeExtendedAttributes(String id, String[] extendedAttributeNames) throws NmsWANEdgeException;


  /**
   * Add each of the specified CustomAttributes to each of the IDs provided
   * @param ids
   * @param customAttributes 
   */
  void addExtendedAttributes(String[] ids, CustomAttribute[] customAttributes) throws NmsWANEdgeException;


  void removeExtendedAttributes(String[] ids, String[] customAttributeNames) throws NmsWANEdgeException;

}
