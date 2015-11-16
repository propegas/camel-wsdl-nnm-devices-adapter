package com.hp.ov.nms.sdk.framerelayendpoint;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hp.ov.nms.sdk.inventory.Capability;
import com.hp.ov.nms.sdk.inventory.CustomAttribute;
import com.hp.ov.nms.sdk.inventory.ManagementMode;

/**
 * Public Incident class for SDK incident injection.
 *
 * @author Rocky
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FrameRelayEndpoint implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
    private Capability[] capabilities;
    private Date created;
    private CustomAttribute[] customAttributes;
    private Long dlci;
    private String id;
    private String interfaceId;
    private ManagementMode managementMode;
    private Date modified;
    private String notes;
    private String uuid;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public Long getDlci() {
        return dlci;
    }
    public void setDlci(Long dlci) {
        this.dlci = dlci;
    }
    public ManagementMode getManagementMode() {
        return managementMode;
    }
    public void setManagementMode(ManagementMode managementMode) {
        this.managementMode = managementMode;
    }
    public String getInterfaceId() {
        return interfaceId;
    }
    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
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
