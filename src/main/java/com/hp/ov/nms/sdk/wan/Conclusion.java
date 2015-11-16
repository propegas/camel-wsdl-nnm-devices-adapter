/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.ov.nms.sdk.wan;

import java.io.Serializable;

/**
 *
 * @author haswellj
 */
public class Conclusion implements Serializable {

  private String status;
  private String conclusion;
  private String incidentID;

  public Conclusion(String status, String conclusion, String incidentID) {
    this.status = status;
    this.conclusion = conclusion;
    this.incidentID = incidentID;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the conclusion
   */
  public String getConclusion() {
    return conclusion;
  }

  /**
   * @param conclusion the conclusion to set
   */
  public void setConclusion(String conclusion) {
    this.conclusion = conclusion;
  }

  /**
   * @return the incidentID
   */
  public String getIncidentID() {
    return incidentID;
  }

  /**
   * @param incidentID the incidentID to set
   */
  public void setIncidentID(String incidentID) {
    this.incidentID = incidentID;
  }

  
}
