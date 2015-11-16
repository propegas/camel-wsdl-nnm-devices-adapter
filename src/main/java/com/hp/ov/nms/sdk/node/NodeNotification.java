package com.hp.ov.nms.sdk.node;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.hp.ov.nms.sdk.SdkNotification;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.inventory.ManagementMode;

/**
 * Public IncidentEvent class for SDK incident subscription.
 *
 * @author Rocky
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NodeNotification implements Serializable, SdkNotification {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private CustomAttribute[] customAttributes = new CustomAttribute[0];
    private Boolean discoveryAnalysisUpdated=false;
    private Date discoveryLastCompleted=null;
    private String event="";
    private String id="";
    private Boolean isSnmpSupported=false;
    private String longName="";
    private ManagementMode managementMode=ManagementMode.NOTMANAGED;
    private int nodeResent=0;
    private Date updateTime=null;
    private String uuid="";

    public String toXML() {
        String result="";
        result+="<event>"+event+"</event>";
        result+="<id>"+id+"</id>";
        result+="<uuid>"+uuid+"</uuid>";
        result+="<longName>"+longName+"</longName>";
        result+="<isSnmpSupported>"+isSnmpSupported+"</isSnmpSupported>";
        result+="<nodeResent>"+nodeResent+"</nodeResent>";
        result+="<discoveryAnalysisUpdated>"+discoveryAnalysisUpdated+"</discoveryAnalysisUpdated>";
        String date=format(updateTime);
        if(!date.equals("")) result+="<updateTime>"+date+"</updateTime>";
        date=format(discoveryLastCompleted);
        if(!date.equals("")) result+="<discoveryLastCompleted>"+date+"</discoveryLastCompleted>";
        result+="<managementMode>"+((managementMode==null)?"":managementMode)+"</managementMode>";
        if(customAttributes!=null) {
            for(CustomAttribute ca: customAttributes) {
                result+="<customAttributes>";
                result+=ca.toXML();
                result+="</customAttributes>";
            }
        }
        return result;
    }

    private String format(Date date) {
        XMLGregorianCalendar xgCal = null;
        if(date!=null) {
            GregorianCalendar gCal = new GregorianCalendar();
            gCal.setTime(date);
            try {
              xgCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
            } catch (DatatypeConfigurationException e) {
                return "";
            }
        }

        return (xgCal==null)?"":xgCal.toXMLFormat();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String nodeId) {
        this.id = nodeId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean isDiscoveryAnalysisUpdated() {
        return discoveryAnalysisUpdated;
    }

    public void setDiscoveryAnalysisUpdated(Boolean discoveryAnalysisUpdated) {
        this.discoveryAnalysisUpdated = discoveryAnalysisUpdated;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public CustomAttribute[] getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(CustomAttribute[] customAttributes) {
        this.customAttributes=customAttributes;
    }

    public Date getDiscoveryLastCompleted() {
        return discoveryLastCompleted;
    }

    public void setDiscoveryLastCompleted(Date discoveryLastCompleted) {
        this.discoveryLastCompleted = discoveryLastCompleted;
    }

    public ManagementMode getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(ManagementMode managementMode) {
        this.managementMode = managementMode;
    }

    public int getNodeResent() {
        return nodeResent;
    }

    public void setNodeResent(int nodeResent) {
        this.nodeResent = nodeResent;
    }

    public Boolean getDiscoveryAnalysisUpdated() {
        return discoveryAnalysisUpdated;
    }

    public Boolean getIsSnmpSupported() {
        return isSnmpSupported;
    }

    public void setIsSnmpSupported(Boolean isSnmpSupported) {
        this.isSnmpSupported = isSnmpSupported;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

}
