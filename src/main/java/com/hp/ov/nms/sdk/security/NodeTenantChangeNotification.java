package com.hp.ov.nms.sdk.security;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.hp.ov.nms.sdk.SdkNotification;

@XmlAccessorType(XmlAccessType.FIELD)
public class NodeTenantChangeNotification implements Serializable, SdkNotification {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!

    private static final long serialVersionUID = 1L;

    private String currentTenantName="";
    private String currentTenantUuid="";
    private String nodeUuid="";
    private Date occurrenceTime=null;
    private String previousTenantName="";
    private String previousTenantUuid="";

    public NodeTenantChangeNotification(){}

    public NodeTenantChangeNotification(String nodeUuid,
            String currentTenantName, String currentTenantUuid,
            String previousTenantName, String previousTenantUuid,
            Date occurrenceTime) {
        super();
        this.nodeUuid = nodeUuid;
        this.currentTenantName = currentTenantName;
        this.currentTenantUuid = currentTenantUuid;
        this.previousTenantName = previousTenantName;
        this.previousTenantUuid = previousTenantUuid;
        this.occurrenceTime = occurrenceTime;
    }

    public String getNodeUuid() {
        return nodeUuid;
    }

    public void setNodeUuid(String nodeUuid) {
        this.nodeUuid = nodeUuid;
    }

    public String getCurrentTenantName() {
        return currentTenantName;
    }

    public void setCurrentTenantName(String currentTenantName) {
        this.currentTenantName = currentTenantName;
    }

    public String getCurrentTenantUuid() {
        return currentTenantUuid;
    }

    public void setCurrentTenantUuid(String currentTenantUuid) {
        this.currentTenantUuid = currentTenantUuid;
    }

    public String getPreviousTenantName() {
        return previousTenantName;
    }

    public void setPreviousTenantName(String previousTenantName) {
        this.previousTenantName = previousTenantName;
    }

    public String getPreviousTenantUuid() {
        return previousTenantUuid;
    }

    public void setPreviousTenantUuid(String previousTenantUuid) {
        this.previousTenantUuid = previousTenantUuid;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    /*
        <xs:element name='nodeUuid' type='xs:string'/>
        <xs:element name='currentTenantName' type='xs:string'/>
        <xs:element name='currentTenantUuid' type='xs:string'/>
        <xs:element name='previousTenantName' type='xs:string'/>
        <xs:element name='previousTenantUuid' type='xs:string'/>
        <xs:element name='occurrenceTime' type='xs:dateTime'/>
    */
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        buildTag(sb,"nodeUuid",this.nodeUuid);
        buildTag(sb,"currentTenantName",this.currentTenantName);
        buildTag(sb,"currentTenantUuid",this.currentTenantUuid);
        buildTag(sb,"previousTenantName",this.previousTenantName);
        buildTag(sb,"previousTenantUuid",this.previousTenantUuid);
        buildTag(sb,"occurrenceTime",format(this.occurrenceTime));
        return sb.toString();
    }

    static void buildTag(StringBuilder sb, String tagName, String value) {
        beginTag(sb, tagName);
        sb.append(value);
        endTag(sb, tagName);
    }

    static void beginTag(StringBuilder sb, String tagName) {
        sb.append('<');
        sb.append(tagName);
        sb.append('>');
    }

    static void endTag(StringBuilder sb, String tagName) {
        sb.append("</");
        sb.append(tagName);
        sb.append('>');
    }

    static String format(Date date) {
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

    @Override
    public String toString() {
        return "NodeTenantChangeNotification [nodeUuid=" + nodeUuid
                + ", currentTenantName=" + currentTenantName
                + ", currentTenantUuid=" + currentTenantUuid
                + ", previousTenantName=" + previousTenantName
                + ", previousTenantUuid=" + previousTenantUuid
                + ", occurrenceTime=" + occurrenceTime + "]";
    }

}
