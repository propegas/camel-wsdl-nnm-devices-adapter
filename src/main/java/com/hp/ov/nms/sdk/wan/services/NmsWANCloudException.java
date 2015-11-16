/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan.services;

import javax.xml.ws.WebFault;

import com.hp.ov.nms.sdk.wan.NmsWanFault;
import com.hp.ov.nms.sdk.wan.WanCloud;

/**
 *
 * @author haswellj
 */
@WebFault(
  name = "NmsWanCloudFault", 
  targetNamespace = "http://wan.sdk.nms.ov.hp.com"
)
public class NmsWANCloudException extends Exception {

  private NmsWanFault faultInfo;

  public NmsWANCloudException(String message) {
    super(message);
    this.faultInfo = new NmsWanFault(WanCloud.class, message);
  }

  public NmsWanFault getFaultInfo() {
    return faultInfo;
  }

}
