/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.CustomAttribute;

/**
 *
 * @author haswellj
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WanCloud {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
  private Long asNumber;
  private Conclusion[] conclusions;
  private CustomAttribute[] extendedAttributes;
  private String id;
  private String name;
  private String[] wanEdgeIds;


  public WanCloud() {
  }

  public Long getAsNumber() {
    return asNumber;
  }

  public void setAsNumber(Long asNumber) {
    this.asNumber = asNumber;
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

  public String[] getWanEdgeIds() {
    return wanEdgeIds;
  }

  public void setWanEdgeIds(String[] wanEdgeIds) {
    this.wanEdgeIds = wanEdgeIds;
  }
}
