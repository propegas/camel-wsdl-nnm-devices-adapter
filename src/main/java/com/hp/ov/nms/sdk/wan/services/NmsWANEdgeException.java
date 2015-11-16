/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan.services;

import javax.xml.ws.WebFault;

import com.hp.ov.nms.sdk.wan.NmsWanFault;
import com.hp.ov.nms.sdk.wan.WanEdge;

/**
 *
 * @author haswellj
 */
@WebFault(
  name = "NmsWanEdgeFault", 
  targetNamespace = "http://wan.sdk.nms.ov.hp.com"
)
public class NmsWANEdgeException extends Exception {

  private NmsWanFault faultInfo;
  public NmsWANEdgeException(String message) {
    super(message);
    this.faultInfo = new NmsWanFault(WanEdge.class, message);
  }

  public NmsWanFault getFaultInfo() {
    return faultInfo;
  }
}
