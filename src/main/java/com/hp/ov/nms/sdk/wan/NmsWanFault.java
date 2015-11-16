/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author haswellj
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
  name = "NmsWanFault",
  propOrder = {
    "message",
    "offendingClass"
  }
)
public class NmsWanFault implements Serializable {

  private String message;
  private String offendingClass;


  public NmsWanFault() {
    //Required for mapping
  }


  public NmsWanFault(String offendingClass, String message) {
    this.message = message;
    this.offendingClass = offendingClass;
  }

  public NmsWanFault(Class offendingClass, String message) {
    this(offendingClass.getName(), message);
  }

  /**
   * @return the Message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param Message the Message to set
   */
  public void setMessage(String Message) {
    this.message = Message;
  }

  /**
   * @return the offendingClass
   */
  public String getOffendingClass() {
    return offendingClass;
  }

  /**
   * @param offendingClass the offendingClass to set
   */
  public void setOffendingClass(String offendingClass) {
    this.offendingClass = offendingClass;
  }



}
