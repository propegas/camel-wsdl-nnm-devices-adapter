/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan.services;

import javax.xml.ws.WebFault;

import com.hp.ov.nms.sdk.wan.NmsWanFault;
import com.hp.ov.nms.sdk.wan.WanConnection;

/**
 *
 * @author haswellj
 */
@WebFault(
  name="NmsWanConnectionFault",
  targetNamespace = "http://wan.sdk.nms.ov.hp.com"
)
public class NmsWANConnectionException extends Exception {

  private NmsWanFault faultInfo;
  public NmsWANConnectionException(String message) {
    super(message);
    this.faultInfo = new NmsWanFault(WanConnection.class, message);
  }


  public NmsWanFault getFaultInfo() {
    return faultInfo;
  }
}
