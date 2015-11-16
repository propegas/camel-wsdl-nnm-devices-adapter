package com.hp.ov.nms.sdk.incident;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.hp.ov.nms.sdk.SdkNotification;
/**
 * Public IncidentEvent class for SDK incident subscription.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentNotification implements Serializable, SdkNotification {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private Cia[] cias=new Cia[0];
    private String category="";
    private Date created=null;
    private int duplicateCount=0;
    private String id="";
    private int incidentResent=0;
    private String family="";
    private Date firstOccurrenceTime=null;
    private String formattedMessage="";
    private Date lastOccurrenceTime=null;
    private String lifecycleState="";
    private String name="";
    private Nature nature=Nature.NONE;
    private Origin origin=Origin.OTHER;
    private Date originOccurrenceTime=null;
    private String previousLifecycleState="";
    private String previousRcaActive="";
    private String priority="";
    private boolean rcaActive=false;
    private Severity severity=Severity.MINOR;
    private String sourceName="";
    private String sourceNodeLongName="";
    private String sourceNodeName="";
    private String sourceNodeUuid="";
    private String sourceUuid="";
    private Date updateTime=null;
    private String uuid="";

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getDuplicateCount() {
        return duplicateCount;
    }
    public void setDuplicateCount(int duplicateCount) {
        this.duplicateCount = duplicateCount;
    }
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public Date getFirstOccurrenceTime() {
        return firstOccurrenceTime;
    }
    public void setFirstOccurrenceTime(Date firstOccurrenceTime) {
        this.firstOccurrenceTime = firstOccurrenceTime;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getLastOccurrenceTime() {
        return lastOccurrenceTime;
    }
    public void setLastOccurrenceTime(Date lastOccurrenceTime) {
        this.lastOccurrenceTime = lastOccurrenceTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Nature getNature() {
        return nature;
    }
    public void setNature(Nature nature) {
        this.nature = nature;
    }
    public Origin getOrigin() {
        return origin;
    }
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
    public Date getOriginOccurrenceTime() {
        return originOccurrenceTime;
    }
    public void setOriginOccurrenceTime(Date originOccurrenceTime) {
        this.originOccurrenceTime = originOccurrenceTime;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public boolean isRcaActive() {
        return rcaActive;
    }
    public void setRcaActive(boolean rcaActive) {
        this.rcaActive = rcaActive;
    }
    public Severity getSeverity() {
        return severity;
    }
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
    public String getSourceNodeUuid() {
        return sourceNodeUuid;
    }
    public void setSourceNodeUuid(String sourceNodeUuid) {
        this.sourceNodeUuid = sourceNodeUuid;
    }
    public String getSourceUuid() {
        return sourceUuid;
    }
    public void setSourceUuid(String sourceUuid) {
        this.sourceUuid = sourceUuid;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    public String getSourceNodeName() {
        return sourceNodeName;
    }
    public void setSourceNodeName(String sourceNodeName) {
        this.sourceNodeName = sourceNodeName;
    }

    public String toXML() {
        String result="";
        result+="<id>"+id+"</id>";
        result+="<uuid>"+uuid+"</uuid>";
        result+="<name>"+name+"</name>";
        result+="<sourceUuid>"+sourceUuid+"</sourceUuid>";
        result+="<sourceName><![CDATA["+sourceName+"]]></sourceName>";
        result+="<sourceNodeUuid>"+sourceNodeUuid+"</sourceNodeUuid>";
        result+="<sourceNodeName><![CDATA["+sourceNodeName+"]]></sourceNodeName>";
        result+="<sourceNodeLongName><![CDATA["+sourceNodeLongName+"]]></sourceNodeLongName>";
        result+="<lifecycleState>"+lifecycleState+"</lifecycleState>";
        result+="<severity>"+severity+"</severity>";
        result+="<priority>"+priority+"</priority>";
        result+="<category>"+category+"</category>";
        result+="<family>"+family+"</family>";
        result+="<nature>"+nature+"</nature>";
        result+="<origin>"+origin+"</origin>";
        result+="<duplicateCount>"+duplicateCount+"</duplicateCount>";
        result+="<rcaActive>"+rcaActive+"</rcaActive>";
        result+="<formattedMessage><![CDATA["+formattedMessage+"]]></formattedMessage>";
        String date=format(originOccurrenceTime);
        if(!date.equals("")) result+="<originOccurrenceTime>"+date+"</originOccurrenceTime>";
        date=format(firstOccurrenceTime);
        if(!date.equals("")) result+="<firstOccurrenceTime>"+date+"</firstOccurrenceTime>";
        date=format(lastOccurrenceTime);
        if(!date.equals("")) result+="<lastOccurrenceTime>"+date+"</lastOccurrenceTime>";
        result+="<incidentResent>"+incidentResent+"</incidentResent>";
        date=format(created);
        if(!date.equals("")) result+="<created>"+date+"</created>";
        date=format(updateTime);
        if(!date.equals("")) result+="<updateTime>"+date+"</updateTime>";
        result+="<previousLifecycleState>"+previousLifecycleState+"</previousLifecycleState>";
        result+="<previousRcaActive>"+previousRcaActive+"</previousRcaActive>";
        if(cias!=null) {
            for(Cia cia: cias) {
                result+="<cias>";
                result+=cia.toXML();
                result+="</cias>";
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

    public String getFormattedMessage() {
        return formattedMessage;
    }
    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getPreviousLifecycleState() {
        return previousLifecycleState;
    }
    public void setPreviousLifecycleState(String previousLifecycleState) {
        this.previousLifecycleState = previousLifecycleState;
    }
    public String getPreviousRcaActive() {
        return previousRcaActive;
    }
    public void setPreviousRcaActive(String previousRcaActive) {
        this.previousRcaActive = previousRcaActive;
    }
    public String getLifecycleState() {
        return lifecycleState;
    }
    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }
    public Cia[] getCias() {
        return cias;
    }
    public void setCias(Cia[] cias) {
        this.cias = cias;
    }
    public int getIncidentResent() {
        return incidentResent;
    }
    public void setIncidentResent(int incidentResent) {
        this.incidentResent = incidentResent;
    }
    public String getSourceNodeLongName() {
        return sourceNodeLongName;
    }
    public void setSourceNodeLongName(String sourceNodeLongName) {
        this.sourceNodeLongName = sourceNodeLongName;
    }
}
