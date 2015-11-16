package com.hp.ov.nms.sdk.incident;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Public Incident class for SDK incident injection.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Incident implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    String assignedTo;
    String category;
    Cia[] cias;
    Date created;
    int duplicateCount;
    String family;
    Date firstOccurrenceTime;
    String formattedMessage;
    String id;
    Date lastOccurrenceTime;
    String lifecycleState;
    Date modified;
    String name;
    Nature nature;
    String notes;
    Origin origin;
    Date originOccurrenceTime;
    String priority;
    boolean rcaActive;
    Severity severity;
    String sourceName;
    String sourceNodeName;
    String sourceNodeUuid;
    String sourceType;
    String sourceUuid;
    String uuid;

    public String getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Cia[] getCias() {
        return cias;
    }
    public void setCias(Cia[] cias) {
        this.cias = cias;
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
    public String getLifecycleState() {
        return lifecycleState;
    }
    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
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
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
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
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
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
    public String getFormattedMessage() {
        return formattedMessage;
    }
    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

}
