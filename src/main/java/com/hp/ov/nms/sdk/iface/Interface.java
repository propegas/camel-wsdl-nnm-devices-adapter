package com.hp.ov.nms.sdk.iface;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.inventory.ManagementMode;
import com.hp.ov.nms.sdk.inventory.Status;

/**
 * Public Interface class for SDK incident injection.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Interface implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private String administrativeState;
    private Capability[] capabilities;
    private String connectionId;
    private Date created;
    private CustomAttribute[] customAttributes;
    private String hostedOnId;
    private String id;
    private String ifAlias;
    private String ifDescr;
    private long ifIndex;
    private String ifName;
    private long ifSpeed;
    private String ifType;
    private ManagementMode managementMode;
    private Date modified;
    private String name;
    private String notes;
    private String operationalState;
    private String physicalAddress;
    private Status status;
    private String uuid;

    public String getAdministrativeState() {
        return administrativeState;
    }
    public void setAdministrativeState(String administrativeState) {
        this.administrativeState = administrativeState;
    }
    public String getConnectionId() {
        return connectionId;
    }
    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getHostedOnId() {
        return hostedOnId;
    }
    public void setHostedOnId(String hostedOnId) {
        this.hostedOnId = hostedOnId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIfAlias() {
        return ifAlias;
    }
    public void setIfAlias(String ifAlias) {
        this.ifAlias = ifAlias;
    }
    public String getIfDescr() {
        return ifDescr;
    }
    public void setIfDescr(String ifDescr) {
        this.ifDescr = ifDescr;
    }
    public long getIfIndex() {
        return ifIndex;
    }
    public void setIfIndex(long ifIndex) {
        this.ifIndex = ifIndex;
    }
    public String getIfName() {
        return ifName;
    }
    public void setIfName(String ifName) {
        this.ifName = ifName;
    }
    public long getIfSpeed() {
        return ifSpeed;
    }
    public void setIfSpeed(long ifSpeed) {
        this.ifSpeed = ifSpeed;
    }
    public String getIfType() {
        return ifType;
    }
    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public ManagementMode getManagementMode() {
        return managementMode;
    }
    public void setManagementMode(ManagementMode managementMode) {
        this.managementMode = managementMode;
    }
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getOperationalState() {
        return operationalState;
    }
    public void setOperationalState(String operationalState) {
        this.operationalState = operationalState;
    }
    public String getPhysicalAddress() {
        return physicalAddress;
    }
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public CustomAttribute[] getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(CustomAttribute[] customAttributes) {
        this.customAttributes = customAttributes;
    }

    public Capability[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capability[] capabilities) {
        this.capabilities = capabilities;
    }

}
