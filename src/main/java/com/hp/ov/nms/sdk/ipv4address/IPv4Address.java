package com.hp.ov.nms.sdk.ipv4address;

import java.io.Serializable;
import java.util.Date;

import com.hp.ov.nms.sdk.inventory.ManagementMode;

/**
 * Public IPv4Address class for SDK address inventory.
 *
 * @deprecated Use com.hp.ov.nms.sdk.ipaddress.IPAddress
 * @author Rocky
 *
 */
public class IPv4Address implements Serializable {
    private String id;
    private String uuid;
    private String ipValue;
    private String hostedOnId;
    /** @deprecated */
    private boolean isVirtual;
    private String ipSubnetId;
    private String inInterfaceId;
    private int prefixLength;
    private ManagementMode managementMode;
    private String notes;
    private Date created;
    private Date modified;

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
    public String getIpSubnetId() {
        return ipSubnetId;
    }
    public void setIpSubnetId(String ipSubnetId) {
        this.ipSubnetId = ipSubnetId;
    }
    public String getIpValue() {
        return ipValue;
    }
    public void setIpValue(String ipValue) {
        this.ipValue = ipValue;
    }
    /** @deprecated */
    public boolean isVirtual() {
        return isVirtual;
    }
    /** @deprecated */
    public void setVirtual(boolean isVirtual) {
        this.isVirtual = isVirtual;
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
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public int getPrefixLength() {
        return prefixLength;
    }
    public void setPrefixLength(int prefixLength) {
        this.prefixLength = prefixLength;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getInInterfaceId() {
        return inInterfaceId;
    }
    public void setInInterfaceId(String inInterfaceId) {
        this.inInterfaceId = inInterfaceId;
    }
}
