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
public class NodeSecurityChangeNotification implements Serializable, SdkNotification {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private static final long serialVersionUID = 1L;

    private String currentSecurityGroupName="";
    private String currentSecurityGroupUuid="";
    private String nodeUuid="";
    private Date occurrenceTime=null;
    private String previousSecurityGroupName="";
    private String previousSecurityGroupUuid="";

    public NodeSecurityChangeNotification(){}

    public NodeSecurityChangeNotification(String nodeUuid,
            String currentSecurityGroupName, String currentSecurityGroupUuid,
            String previousSecurityGroupName, String previousSecurityGroupUuid,
            Date occurrenceTime) {
        super();
        this.nodeUuid = nodeUuid;
        this.currentSecurityGroupName = currentSecurityGroupName;
        this.currentSecurityGroupUuid = currentSecurityGroupUuid;
        this.previousSecurityGroupName = previousSecurityGroupName;
        this.previousSecurityGroupUuid = previousSecurityGroupUuid;
        this.occurrenceTime = occurrenceTime;
    }

    public String getNodeUuid() {
        return nodeUuid;
    }

    public void setNodeUuid(String nodeUuid) {
        this.nodeUuid = nodeUuid;
    }

    public String getCurrentSecurityGroupName() {
        return currentSecurityGroupName;
    }

    public void setCurrentSecurityGroupName(String currentSecurityGroupName) {
        this.currentSecurityGroupName = currentSecurityGroupName;
    }

    public String getCurrentSecurityGroupUuid() {
        return currentSecurityGroupUuid;
    }

    public void setCurrentSecurityGroupUuid(String currentSecurityGroupUuid) {
        this.currentSecurityGroupUuid = currentSecurityGroupUuid;
    }

    public String getPreviousSecurityGroupName() {
        return previousSecurityGroupName;
    }

    public void setPreviousSecurityGroupName(String previousSecurityGroupName) {
        this.previousSecurityGroupName = previousSecurityGroupName;
    }

    public String getPreviousSecurityGroupUuid() {
        return previousSecurityGroupUuid;
    }

    public void setPreviousSecurityGroupUuid(String previousSecurityGroupUuid) {
        this.previousSecurityGroupUuid = previousSecurityGroupUuid;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    /*
        <xs:element name='nodeUuid' type='xs:string'/>
        <xs:element name='currentSecurityGroupName' type='xs:string'/>
        <xs:element name='currentSecurityGroupUuid' type='xs:string'/>
        <xs:element name='previousSecurityGroupName' type='xs:string'/>
        <xs:element name='previousSecurityGroupUuid' type='xs:string'/>
        <xs:element name='occurrenceTime' type='xs:dateTime'/>
    */
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        buildTag(sb,"nodeUuid",this.nodeUuid);
        buildTag(sb,"currentSecurityGroupName",this.currentSecurityGroupName);
        buildTag(sb,"currentSecurityGroupUuid",this.currentSecurityGroupUuid);
        buildTag(sb,"previousSecurityGroupName",this.previousSecurityGroupName);
        buildTag(sb,"previousSecurityGroupUuid",this.previousSecurityGroupUuid);
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
        return "NodeSecurityChangeNotification [nodeUuid=" + nodeUuid
                + ", currentSecurityGroupName=" + currentSecurityGroupName
                + ", currentSecurityGroupUuid=" + currentSecurityGroupUuid
                + ", previousSecurityGroupName=" + previousSecurityGroupName
                + ", previousSecurityGroupUuid=" + previousSecurityGroupUuid
                + ", occurrenceTime=" + occurrenceTime + "]";
    }

}
