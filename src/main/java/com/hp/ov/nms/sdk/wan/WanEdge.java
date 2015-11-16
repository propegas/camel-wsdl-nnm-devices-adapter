/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.CustomAttribute;

/**
 *
 * @author haswellj
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WanEdge implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
  private Conclusion[] conclusions;
  private String edgeIpAddressState;
  private CustomAttribute[] extendedAttributes;
  private String id;
  private String name;
  private String peIPAddress;
  private String wanCloudId;
  private String[] wanConnectionIds[];

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Conclusion[] getConclusions() {
    return conclusions;
  }

  public void setConclusions(Conclusion[] conclusions) {
    this.conclusions = conclusions;
  }

  public String getEdgeIpAddressState() {
    return edgeIpAddressState;
  }

  public void setEdgeIpAddressState(String edgeIpAddressState) {
    this.edgeIpAddressState = edgeIpAddressState;
  }

  public CustomAttribute[] getExtendedAttributes() {
    return extendedAttributes;
  }

  public void setExtendedAttributes(CustomAttribute[] extendedAttributes) {
    this.extendedAttributes = extendedAttributes;
  }

  public String getPeIPAddress() {
    return peIPAddress;
  }

  public void setPeIPAddress(String peIPAddress) {
    this.peIPAddress = peIPAddress;
  }

  public String getWanCloudId() {
    return wanCloudId;
  }

  public void setWanCloudId(String wanCloudId) {
    this.wanCloudId = wanCloudId;
  }

  public String[][] getWanConnectionIds() {
    return wanConnectionIds;
  }

  public void setWanConnectionIds(String[][] wanConnectionIds) {
    this.wanConnectionIds = wanConnectionIds;
  }
}
