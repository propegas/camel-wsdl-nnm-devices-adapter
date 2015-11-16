package com.hp.ov.nms.sdk.ipaddress;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.ManagementMode;

/**
 * Public Incident class for SDK incident injection.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IPAddress implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private Date created;
    private String hostedOnId;
    private String id;
    private String inInterfaceId;
    private String ipSubnetId;
    private String ipValue;
    private ManagementMode managementMode;
    private Date modified;
    private String notes;
    private int prefixLength;
    private String uuid;

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
