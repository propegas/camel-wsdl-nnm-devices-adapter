package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.ws.WebFault;

@WebFault(name = "NmsIncidentConfigFault", targetNamespace = "http://incidentconfiguration.sdk.nms.ov.hp.com/")
public class NmsIncidentConfigException extends Exception {


    private NmsIncidentConfigFault faultInfo;

    /**
    *
    * @param faultInfo
    * @param message
    */
   public NmsIncidentConfigException(String message, NmsIncidentConfigFault faultInfo) {
       super(message);
       this.faultInfo = faultInfo;
   }

   /**
   *
   * @param faultInfo
   * @param message
   * @param cause
   */
  public NmsIncidentConfigException(String message, NmsIncidentConfigFault faultInfo, Throwable cause) {
      super(message, cause);
      this.faultInfo = faultInfo;
  }

  /**
   *
   * @return
   *     returns fault bean: com.hp.ov.nms.sdk.node. NmsIncidentConfigFault
   */
  public NmsIncidentConfigFault getFaultInfo() {
      return faultInfo;
  }



}
