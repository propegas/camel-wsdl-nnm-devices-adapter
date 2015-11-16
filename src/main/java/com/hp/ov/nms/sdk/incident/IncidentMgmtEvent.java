package com.hp.ov.nms.sdk.incident;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentMgmtEvent implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    Cia[] cias=new Cia[0];
    String name="";
    Nature nature=Nature.NONE;
    Date originOccurrenceTime=null;
    String priority="com.hp.nms.incident.priority.None";
    String sourceName="";
    String sourceNodeName="";
    String sourceNodeUuid="";
    String sourceType="";
    String sourceUuid="";
    String uuid="";

    public Cia[] getCias() {
        return cias;
    }
    public void setCias(Cia[] cias) {
        this.cias = cias;
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
    public Date getOriginOccurrenceTime() {
        return originOccurrenceTime;
    }
    public void setOriginOccurrenceTime(Date originOccurrenceTime) {
        this.originOccurrenceTime = originOccurrenceTime;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
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
    public String getSourceNodeUuid() {
        return sourceNodeUuid;
    }
    public void setSourceNodeUuid(String sourceNodeUuid) {
        this.sourceNodeUuid = sourceNodeUuid;
    }
    public String getSourceType() {
        return sourceType;
    }
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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
}
