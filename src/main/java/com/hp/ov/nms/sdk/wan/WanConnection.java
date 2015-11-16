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
 * @author haswellj
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WanConnection implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
  private String administrativeState;
  private String ceIPAddress;
  private Conclusion[] conclusions;
  private CustomAttribute[] extendedAttributes;
  private String id;
  private String ifaceId;
  private String layer;
  private String name;
  private String operationalState;
  private String peeringState;
  private String source;
  private String type;
  private String wanEdgeId;

  public String getAdministrativeState() {
    return administrativeState;
  }

  public void setAdministrativeState(String administrativeState) {
    this.administrativeState = administrativeState;
  }

  public String getCeIPAddress() {
    return ceIPAddress;
  }

  public void setCeIPAddress(String ceIPAddress) {
    this.ceIPAddress = ceIPAddress;
  }

  public Conclusion[] getConclusions() {
    return conclusions;
  }

  public void setConclusions(Conclusion[] conclusions) {
    this.conclusions = conclusions;
  }

  public CustomAttribute[] getExtendedAttributes() {
    return extendedAttributes;
  }

  public void setExtendedAttributes(CustomAttribute[] extendedAttributes) {
    this.extendedAttributes = extendedAttributes;
  }

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

  public String getIfaceId() {
    return ifaceId;
  }

  public void setIfaceId(String ifaceId) {
    this.ifaceId = ifaceId;
  }

  public String getLayer() {
    return layer;
  }

  public void setLayer(String layer) {
    this.layer = layer;
  }

  public String getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(String operationalState) {
    this.operationalState = operationalState;
  }

  public String getPeeringState() {
    return peeringState;
  }

  public void setPeeringState(String peeringState) {
    this.peeringState = peeringState;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getWanEdgeId() {
    return wanEdgeId;
  }

  public void setWanEdgeId(String wanEdgeId) {
    this.wanEdgeId = wanEdgeId;
  }


  public WanConnection() {
  }


}
